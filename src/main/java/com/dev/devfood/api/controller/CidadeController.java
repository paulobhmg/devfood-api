package com.dev.devfood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.devfood.api.model.CidadeXmlWrapper;
import com.dev.devfood.domain.model.Cidade;
import com.dev.devfood.domain.repository.CidadeRepository;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Cidade>> list() {
		return ResponseEntity.ok(repository.list());
	}
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<CidadeXmlWrapper> getWrapper(){
		return ResponseEntity.ok(new CidadeXmlWrapper(repository.list()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cidade> findById(@PathVariable Long id){
		Cidade cidade = repository.findById(id);
		return cidade != null ? ResponseEntity.ok(cidade) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Cidade> save(@RequestBody Cidade cidade){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(cidade));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cidade> update(@PathVariable Long id, @RequestBody Cidade cidade){
		Cidade cidadeAtual = repository.findById(id);
		if(cidadeAtual != null) {
			BeanUtils.copyProperties(cidade, cidadeAtual, "id", "estado");
			return ResponseEntity.ok(repository.save(cidadeAtual));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Cidade> delete(@PathVariable Long id){
		Cidade cidade = repository.findById(id);
		if(cidade != null) {
			repository.delete(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}

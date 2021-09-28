package com.dev.devfood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.dev.devfood.api.model.CozinhaXmlWrapper;
import com.dev.devfood.domain.model.Cozinha;
import com.dev.devfood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CadastroCozinhaService service;
	
	@GetMapping()
	public ResponseEntity<List<Cozinha>> list() {
		return ResponseEntity.ok(service.list());
	}
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<CozinhaXmlWrapper> getXml() {
		return ResponseEntity.ok(new CozinhaXmlWrapper(service.list()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> findById(@PathVariable Long id) {
		Cozinha cozinha = service.findById(id);
		return cozinha != null ? ResponseEntity.ok(cozinha) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Cozinha> save(@RequestBody Cozinha cozinha){
		return ResponseEntity.ok(service.save(cozinha));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> update(@PathVariable Long id, @RequestBody Cozinha cozinha){
		Cozinha cozinhaAtual = service.findById(id);
		if(cozinhaAtual != null) {
			BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			return ResponseEntity.ok(service.save(cozinhaAtual));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Cozinha> delete(@PathVariable Long id){
		Cozinha cozinha = service.findById(id);
		if(cozinha != null) {
			try {
				service.delete(id);
				return ResponseEntity.noContent().build();
			}catch(DataIntegrityViolationException e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
		}
		return ResponseEntity.notFound().build();
	}
}

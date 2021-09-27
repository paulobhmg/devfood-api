package com.dev.devfood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.dev.devfood.api.model.EstadoXmlWrapper;
import com.dev.devfood.domain.model.Estado;
import com.dev.devfood.domain.repository.EstadoRepository;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Estado>> list(){
		return ResponseEntity.ok(repository.list());		
	}
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<EstadoXmlWrapper> getWrapper() {
		return ResponseEntity.ok(new EstadoXmlWrapper(repository.list()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Estado> findById(@PathVariable Long id) {
		Estado estado = repository.findById(id);
		return estado != null ? ResponseEntity.ok(estado) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Estado> save(@RequestBody Estado estado){
		return ResponseEntity.ok(repository.save(estado));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Estado> update(@PathVariable Long id, @RequestBody Estado estado){
		Estado estadoAtual = repository.findById(id);
		if(estadoAtual != null) {
			BeanUtils.copyProperties(estado, estadoAtual, "id");
			return ResponseEntity.ok(repository.save(estadoAtual));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Estado> delete(@PathVariable Long id){
		Estado estado = repository.findById(id);
		if(estado != null) {
			repository.delete(estado);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}

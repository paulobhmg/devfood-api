package com.dev.devfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}

package com.dev.devfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.devfood.api.model.CozinhaXmlWrapper;
import com.dev.devfood.domain.model.Cozinha;
import com.dev.devfood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository repository;
	
	@GetMapping()
	public ResponseEntity<List<Cozinha>> list() {
		return ResponseEntity.ok(repository.list());
	}
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<CozinhaXmlWrapper> getXml() {
		return ResponseEntity.ok(new CozinhaXmlWrapper(repository.list()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> findById(@PathVariable Long id) {
		Cozinha cozinha = repository.findById(id);
		return cozinha != null ? ResponseEntity.ok(cozinha) : ResponseEntity.notFound().build();
	}
}

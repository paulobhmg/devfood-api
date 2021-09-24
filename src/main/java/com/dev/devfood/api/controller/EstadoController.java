package com.dev.devfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.devfood.domain.model.Estado;
import com.dev.devfood.domain.repository.EstadoRepository;

@RestController
@RequestMapping(value = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController {
	
	@Autowired
	private EstadoRepository repository;
	
	@GetMapping
	public List<Estado> list(){
		return repository.list();		
	}
	
	@GetMapping("/{id}")
	public Estado findById(@PathVariable Long id) {
		return repository.findById(id);
	}
}

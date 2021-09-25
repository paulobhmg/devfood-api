package com.dev.devfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.devfood.api.model.RestauranteXmlWrapper;
import com.dev.devfood.domain.model.Restaurante;
import com.dev.devfood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Restaurante>> list(){
		return ResponseEntity.ok(repository.list());
	}

	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<RestauranteXmlWrapper> getWrapper() {
		return ResponseEntity.ok(new RestauranteXmlWrapper(repository.list()));
	}
		
	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> findById(@PathVariable Long id) {
		/* 
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.LOCATION, "http://api.devfood:8080/cozinhas");
		
			return ResponseEntity
				.status(HttpStatus.MOVED_PERMANENTLY)
				.headers(headers)
				.build();
		*/
		
		return ResponseEntity.ok(repository.findById(id));
	}
}

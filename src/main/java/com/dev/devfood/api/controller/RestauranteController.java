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
		return ResponseEntity.ok(repository.listar());
	}

	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<RestauranteXmlWrapper> getWrapper() {
		return ResponseEntity.ok(new RestauranteXmlWrapper(repository.listar()));
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
		
		Restaurante restaurante = repository.buscarPorId(id);
		return restaurante != null ? ResponseEntity.ok(restaurante) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Restaurante> save(@RequestBody Restaurante restaurante) {
		return ResponseEntity.ok(repository.salvar(restaurante));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Restaurante> update(@PathVariable Long id, @RequestBody Restaurante restaurante){
		Restaurante restauranteAtual = repository.buscarPorId(id);
		if(restauranteAtual != null) {
			BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "cozinha");
			return ResponseEntity.ok(repository.salvar(restauranteAtual));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Restaurante> delete(@PathVariable Long id){
		Restaurante restaurante = repository.buscarPorId(id);
		if(restaurante != null) {
			repository.apagar(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}

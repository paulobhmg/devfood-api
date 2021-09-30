package com.dev.devfood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.devfood.api.model.RestauranteXmlWrapper;
import com.dev.devfood.domain.exception.ResourceInUseException;
import com.dev.devfood.domain.exception.ResourceNotFoundException;
import com.dev.devfood.domain.model.Restaurante;
import com.dev.devfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<RestauranteXmlWrapper> getWrapper() {
		return ResponseEntity.ok(new RestauranteXmlWrapper(cadastroRestauranteService.list()));
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Restaurante restaurante) {
		try {
			restaurante = cadastroRestauranteService.save(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		}catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	@GetMapping
	public ResponseEntity<List<Restaurante>> list(){
		return ResponseEntity.ok(cadastroRestauranteService.list());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try{
			return ResponseEntity.ok(cadastroRestauranteService.findByIdOrThrowsResourceNotFoundException(id));
		}catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Restaurante restaurante){
		try {
			Restaurante restauranteAtual = cadastroRestauranteService.findByIdOrThrowsResourceNotFoundException(id);
			BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
			return ResponseEntity.ok(cadastroRestauranteService.save(restauranteAtual));
		}catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> campos){
			Restaurante restauranteAtual = cadastroRestauranteService.findByIdOrThrowsResourceNotFoundException(id);
			merge(campos, restauranteAtual);
			return update(id, restauranteAtual);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		try {
			cadastroRestauranteService.deleteOrThrowsException(id);
			return ResponseEntity.noContent().build();
		}catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch(ResourceInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
	private void merge(Map<String, Object> campos, Restaurante restaurante) {
		campos.forEach((nomeDaChave, valorDoCampo) -> {
			if(nomeDaChave.equals("nome")) {
				restaurante.setNome((String) valorDoCampo);
			}
			if(nomeDaChave.equals("taxaDeEntrega")) {
				restaurante.setTaxaDeEntrega(new BigDecimal((double) valorDoCampo));
			}
		});
	}
}

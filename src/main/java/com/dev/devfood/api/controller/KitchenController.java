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

import com.dev.devfood.api.model.KitchenXmlWrapper;
import com.dev.devfood.domain.exception.ResourceInUseException;
import com.dev.devfood.domain.exception.ResourceNotFoundException;
import com.dev.devfood.domain.model.Kitchen;
import com.dev.devfood.domain.service.KitchenRegistrationService;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {
	
	@Autowired
	private KitchenRegistrationService kitchenRegistrationService;
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<KitchenXmlWrapper> getXml() {
		return ResponseEntity.ok(new KitchenXmlWrapper(kitchenRegistrationService.list()));
	}
	
	@PostMapping
	public ResponseEntity<Kitchen> save(@RequestBody Kitchen kitchen){
		return ResponseEntity.ok(kitchenRegistrationService.save(kitchen));
	}
	
	@GetMapping()
	public ResponseEntity<List<Kitchen>> list() {
		return ResponseEntity.ok(kitchenRegistrationService.list());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(kitchenRegistrationService.findByIdOrThrowsResourceNotFoundException(id));
		}catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Kitchen kitchen){
		try {
			Kitchen currentKitchen = kitchenRegistrationService.findByIdOrThrowsResourceNotFoundException(id);
			BeanUtils.copyProperties(kitchen, currentKitchen, "id");
			return ResponseEntity.ok(kitchenRegistrationService.save(currentKitchen));
		}catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		try {
			kitchenRegistrationService.deleteOrThrowsException(id);
			return ResponseEntity.noContent().build();
		}catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch(ResourceInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}

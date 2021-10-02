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

import com.dev.devfood.api.model.CityXmlWrapper;
import com.dev.devfood.domain.exception.ResourceInUseException;
import com.dev.devfood.domain.exception.ResourceNotFoundException;
import com.dev.devfood.domain.model.City;
import com.dev.devfood.domain.service.CityRegistrationService;

@RestController
@RequestMapping("/cities")
public class CityController {
	
	@Autowired
	private CityRegistrationService cityRegistrationService;
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<CityXmlWrapper> getWrapper(){
		return ResponseEntity.ok(new CityXmlWrapper(cityRegistrationService.list()));
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody City city){
		try {
			city = cityRegistrationService.save(city);
			return ResponseEntity.status(HttpStatus.CREATED).body(city);
		}catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity<List<City>> list() {
		return ResponseEntity.ok(cityRegistrationService.list());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		try {
			City city = cityRegistrationService.findByIdOrThrowsResourceNotFoundException(id);
			return ResponseEntity.ok(city);
		}catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody City city){
		try {
			City currentCity = cityRegistrationService.findByIdOrThrowsResourceNotFoundException(id);
			BeanUtils.copyProperties(city, currentCity, "id");
			return ResponseEntity.ok(cityRegistrationService.save(currentCity));
		}catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		try {
			cityRegistrationService.deleteOrThrowsException(id);
			return ResponseEntity.noContent().build();
		}catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch(ResourceInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}

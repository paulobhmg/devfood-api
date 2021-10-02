package com.dev.devfood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.devfood.api.model.RestaurantXmlWrapper;
import com.dev.devfood.domain.exception.ResourceInUseException;
import com.dev.devfood.domain.exception.ResourceNotFoundException;
import com.dev.devfood.domain.model.Restaurant;
import com.dev.devfood.domain.service.RestaurantRegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/resturants")
public class RestaurantController {

	@Autowired
	private RestaurantRegistrationService restaurantRegistrationService;
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<RestaurantXmlWrapper> getWrapper() {
		return ResponseEntity.ok(new RestaurantXmlWrapper(restaurantRegistrationService.list()));
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Restaurant restaurant) {
		try {
			restaurant = restaurantRegistrationService.save(restaurant);
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
		}catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	@GetMapping
	public ResponseEntity<List<Restaurant>> list(){
		return ResponseEntity.ok(restaurantRegistrationService.list());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try{
			return ResponseEntity.ok(restaurantRegistrationService.findByIdOrThrowsResourceNotFoundException(id));
		}catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Restaurant restaurant){
		try {
			Restaurant currentRestaurant = restaurantRegistrationService.findByIdOrThrowsResourceNotFoundException(id);
			BeanUtils.copyProperties(restaurant, currentRestaurant, "id");
			return ResponseEntity.ok(restaurantRegistrationService.save(currentRestaurant));
		}catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> partialUpdateWithPatch(@PathVariable Long id, @RequestBody Map<String, Object> fields){
		try {
			Restaurant currentRestaurant = restaurantRegistrationService.findByIdOrThrowsResourceNotFoundException(id);
			merge(fields, currentRestaurant);
			return update(id, currentRestaurant);
		}catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		try {
			restaurantRegistrationService.deleteOrThrowsException(id);
			return ResponseEntity.noContent().build();
		}catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch(ResourceInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
	private void merge(Map<String, Object> properties, Restaurant restaurant) {
		ObjectMapper mapper = new ObjectMapper();
		Restaurant restaurantOrigin = mapper.convertValue(properties, Restaurant.class);
		
		properties.forEach((propertyName, propertyValue) -> {
			Field field = ReflectionUtils.findField(Restaurant.class, propertyName);
			field.setAccessible(true);
			Object newValue = ReflectionUtils.getField(field, restaurantOrigin);
			ReflectionUtils.setField(field, restaurant, newValue);
		});
	}
}

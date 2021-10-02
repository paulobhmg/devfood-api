package com.dev.devfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dev.devfood.domain.exception.ResourceInUseException;
import com.dev.devfood.domain.exception.ResourceNotFoundException;
import com.dev.devfood.domain.model.Kitchen;
import com.dev.devfood.domain.model.Restaurant;
import com.dev.devfood.domain.repository.RestaurantRepository;

@Service
public class RestaurantRegistrationService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private KitchenRegistrationService kitchenRegistrationService;
	
	public List<Restaurant> list(){
		return restaurantRepository.findAll();
	}
	
	public Restaurant save(Restaurant restaurant) {
		Kitchen kitchen = kitchenRegistrationService.findByIdOrThrowsResourceNotFoundException(restaurant.getKitchen().getId());
		try {
			return restaurantRepository.save(restaurant);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(String.format("kitchen de código '%d' não existe.", kitchen.getId()));
		}
	}
	
	public Restaurant findByIdOrThrowsResourceNotFoundException(Long id) {
		try {
			return restaurantRepository.findById(id).get();
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(String.format("restaurant de código '%d' não existe.", id));
		}
	}
	
	public void deleteOrThrowsException(Long id) {
		try {
			restaurantRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(String.format("restaurant de código '%d' não existe.", id));
		}catch(DataIntegrityViolationException e) {
			throw new ResourceInUseException(String.format("restaurant de código '%d' está em uso e não pode ser removido.", id));
		}
	}
}

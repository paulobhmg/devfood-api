package com.dev.devfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dev.devfood.domain.exception.ResourceInUseException;
import com.dev.devfood.domain.exception.ResourceNotFoundException;
import com.dev.devfood.domain.model.City;
import com.dev.devfood.domain.model.State;
import com.dev.devfood.domain.repository.CityRepository;

@Service
public class CityRegistrationService {
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private StateRegistrationService cadastrostateService;
	
	public City save(City city) {
		State state = cadastrostateService.findByIdOrThrowsResourceNotFoundException(city.getState().getId());
		try {
			return cityRepository.save(city);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(String.format("state de código '%d' não existe.", state.getId()));
		}
	}
	
	public List<City> list(){
		return cityRepository.findAll();
	}
	
	public City findByIdOrThrowsResourceNotFoundException(Long id) {
		try {
			return cityRepository.findById(id).get();
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(String.format("city de código '%d' não existe.", id));
		}
	}
	
	public void deleteOrThrowsException(Long id) {
		try {
			cityRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new ResourceInUseException(String.format("city de código '%d' está em uso e não poderá ser removida.", id));
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(String.format("city de código '%d' não existe.", id));
		}
	}
}

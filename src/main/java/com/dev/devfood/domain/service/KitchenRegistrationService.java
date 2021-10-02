package com.dev.devfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dev.devfood.domain.exception.ResourceInUseException;
import com.dev.devfood.domain.exception.ResourceNotFoundException;
import com.dev.devfood.domain.model.Kitchen;
import com.dev.devfood.domain.repository.KitchenRepository;

@Service
public class KitchenRegistrationService {
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	public List<Kitchen> list(){
		return kitchenRepository.findAll();
	}
	
	public Kitchen save(Kitchen kitchen) {
		return kitchenRepository.save(kitchen);
	}
	
	public Kitchen findByIdOrThrowsResourceNotFoundException(Long id){
		try {
			return kitchenRepository.findById(id).get();
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(String.format("kitchen de código '%d' não existe.", id));
		}
	}

	public void deleteOrThrowsException(Long id) {
		try {
			kitchenRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(String.format("kitchen de ID '%d' não existe.", id));
		}catch(DataIntegrityViolationException e) {
			throw new ResourceInUseException(String.format("kitchen de ID '%d' está em uso e não pode ser removida.", id));
		}
	}
}

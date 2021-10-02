package com.dev.devfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dev.devfood.domain.exception.ResourceInUseException;
import com.dev.devfood.domain.exception.ResourceNotFoundException;
import com.dev.devfood.domain.model.State;
import com.dev.devfood.domain.repository.StateRepository;

@Service
public class StateRegistrationService {

	@Autowired
	private StateRepository stateRepository;
	
	public State save(State state) {
		return stateRepository.save(state);
	}
	
	public List<State> list(){
		return stateRepository.findAll();
	}
	
	public State findByIdOrThrowsResourceNotFoundException(Long id) {
		try {
			return stateRepository.findById(id).get();
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(String.format("state de código '%d' não existe.", id));
		}
	}
	
	public void deleteOrThrowsException(Long id) {
		try {
			stateRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new ResourceInUseException(String.format("state de código '%d' está em uso e não poderá ser removido.", id));
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(String.format("state de código '%d' não existe.", id));
		}
	}
}

package com.dev.devfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dev.devfood.domain.exception.ResourceInUseException;
import com.dev.devfood.domain.exception.ResourceNotFoundException;
import com.dev.devfood.domain.model.Cozinha;
import com.dev.devfood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public List<Cozinha> list(){
		return cozinhaRepository.list();
	}
	
	public Cozinha save(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	public Cozinha findByIdOrThrowsResourceNotFoundException(Long id){
		try {
			return cozinhaRepository.findById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(String.format("Cozinha de código '%d' não encontrada.", id));
		}
	}

	public void deleteOrThrowsException(Long id) {
		try {
			cozinhaRepository.delete(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(String.format("Cozinha de ID '%d' não encontrada.", id));
		}catch(DataIntegrityViolationException e) {
			throw new ResourceInUseException(String.format("Cozinha de ID '%d' está em uso e não pode ser removida.", id));
		}
	}
}

package com.dev.devfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dev.devfood.domain.exception.ResourceInUseException;
import com.dev.devfood.domain.exception.ResourceNotFoundException;
import com.dev.devfood.domain.model.Cozinha;
import com.dev.devfood.domain.model.Restaurante;
import com.dev.devfood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	public List<Restaurante> list(){
		return restauranteRepository.list();
	}
	
	public Restaurante save(Restaurante restaurante) {
		Cozinha cozinha = cadastroCozinhaService.findByIdOrThrowsResourceNotFoundException(restaurante.getCozinha().getId());
		try {
			return restauranteRepository.save(restaurante);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(String.format("Cozinha de código '%d' não existe.", cozinha.getId()));
		}
	}
	
	public Restaurante findByIdOrThrowsResourceNotFoundException(Long id) {
		try {
			return restauranteRepository.findById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(String.format("Restaurante de código '%d' não existe.", id));
		}
	}
	
	public void deleteOrThrowsException(Long id) {
		try {
			restauranteRepository.delete(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(String.format("Restaurante de código '%d' não existe.", id));
		}catch(DataIntegrityViolationException e) {
			throw new ResourceInUseException(String.format("Restaurante de código '%d' está em uso e não pode ser removido.", id));
		}
	}
}

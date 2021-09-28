package com.dev.devfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.devfood.domain.model.Restaurante;
import com.dev.devfood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	public List<Restaurante> list(){
		return restauranteRepository.list();
	}
	
	public Restaurante save(Restaurante restaurante) {
		return restauranteRepository.save(restaurante);
	}
	
	public Restaurante findByIdOrThrowsResourceNotFoundException(Long id) {
		return restauranteRepository.findById(id);
	}
}

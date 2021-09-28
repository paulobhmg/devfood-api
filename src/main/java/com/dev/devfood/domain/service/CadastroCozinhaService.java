package com.dev.devfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.devfood.domain.model.Cozinha;
import com.dev.devfood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Cozinha save(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	public Cozinha findById(Long id) {
		return cozinhaRepository.findById(id);
	}
	
	public List<Cozinha> list(){
		return cozinhaRepository.list();
	}
	
	public void delete(Long id) {
		cozinhaRepository.delete(id);
	}
}

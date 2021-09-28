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
	
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.salvar(cozinha);
	}
	
	public Cozinha buscarPorId(Long id) {
		return cozinhaRepository.buscarPorId(id);
	}
	
	public List<Cozinha> listar(){
		return cozinhaRepository.listar();
	}
	
	public void deletar(Long id) {
		cozinhaRepository.deletar(id);
	}
}

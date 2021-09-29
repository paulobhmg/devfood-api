package com.dev.devfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dev.devfood.domain.exception.ResourceInUseException;
import com.dev.devfood.domain.exception.ResourceNotFoundException;
import com.dev.devfood.domain.model.Cidade;
import com.dev.devfood.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Cidade save(Cidade cidade) {
		return null;
	}
	
	public List<Cidade> list(){
		return cidadeRepository.list();
	}
	
	public Cidade findByIdOrThrowsResourceNotFoundException(Long id) {
		try {
			return cidadeRepository.findById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(String.format("Cidade de código '%d' não existe.", id));
		}
	}
	
	public void deleteOrThrowsException(Long id) {
		try {
			cidadeRepository.delete(id);
		}catch(DataIntegrityViolationException e) {
			throw new ResourceInUseException(String.format("Cidade de código '%d' está em uso e não poderá ser removida.", id));
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(String.format("Cidade de código '%d' não existe.", id));
		}
	}
}

package com.dev.devfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dev.devfood.domain.exception.ResourceInUseException;
import com.dev.devfood.domain.exception.ResourceNotFoundException;
import com.dev.devfood.domain.model.Estado;
import com.dev.devfood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado save(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	public List<Estado> list(){
		return estadoRepository.list();
	}
	
	public Estado findByIdOrThrowsResourceNotFoundException(Long id) {
		try {
			return estadoRepository.findById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(String.format("Estado de código '%d' não existe.", id));
		}
	}
	
	public void deleteOrThrowsException(Long id) {
		try {
			estadoRepository.delete(id);
		}catch(DataIntegrityViolationException e) {
			throw new ResourceInUseException(String.format("Estado de código '%d' está em uso e não poderá ser removido.", id));
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(String.format("Estado de código '%d' não existe.", id));
		}
	}
}

package com.dev.devfood.infraestructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dev.devfood.domain.model.Cidade;
import com.dev.devfood.domain.repository.CidadeRepository;

@Repository
public class CidadeRepositoryImpl implements CidadeRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	@Override
	public Cidade save(Cidade cidade) {
		return manager.merge(cidade);
	}

	@Override
	public Cidade findById(Long id) {
		Cidade cidade = manager.find(Cidade.class, id);
		checkIfResourceIsNull(cidade);
		return cidade;
	}

	@Override
	public List<Cidade> list() {
		return manager.createQuery("from Cidade", Cidade.class).getResultList();
	}

	@Transactional
	@Override
	public void delete(Long id) {
		manager.remove(findById(id));
	}

	@Override
	public void checkIfResourceIsNull(Cidade resource) {
		if(resource == null) {
			throw new EmptyResultDataAccessException(1);
		}
	}

}

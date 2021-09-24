package com.dev.devfood.infraestructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dev.devfood.domain.model.Estado;
import com.dev.devfood.domain.repository.EstadoRepository;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	@Override
	public Estado save(Estado estado) {
		return manager.merge(estado);
	}

	@Override
	public Estado findById(Long id) {
		return manager.find(Estado.class, id);
	}

	@Override
	public List<Estado> list() {
		return manager.createQuery("from Estado", Estado.class).getResultList();
	}

	@Transactional
	@Override
	public void delete(Estado estado) {
		manager.remove(findById(estado.getId()));
	}
}

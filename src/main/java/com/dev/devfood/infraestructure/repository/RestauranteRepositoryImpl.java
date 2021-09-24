package com.dev.devfood.infraestructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dev.devfood.domain.model.Restaurante;
import com.dev.devfood.domain.repository.RestauranteRepository;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	@Override
	public Restaurante save(Restaurante restaurante) {
		return manager.merge(restaurante);
	}

	@Override
	public Restaurante findById(Long id) {
		return manager.find(Restaurante.class, id);
	}

	@Override
	public List<Restaurante> list() {
		return manager.createQuery("from Restaurante", Restaurante.class).getResultList();
	}

	@Transactional
	@Override
	public void delete(Restaurante restaurante) {
		manager.remove(findById(restaurante.getId()));
	}
}

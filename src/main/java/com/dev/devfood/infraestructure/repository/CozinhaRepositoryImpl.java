package com.dev.devfood.infraestructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dev.devfood.domain.model.Cozinha;
import com.dev.devfood.domain.repository.CozinhaRepository;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository{

	@PersistenceContext
	private EntityManager manager;
		
	@Transactional
	@Override
	public Cozinha save(Cozinha cozinha) {
		return manager.merge(cozinha) ;
	}

	@Override
	public Cozinha findById(Long id) {
		return manager.find(Cozinha.class, id);
	}

	@Override
	public List<Cozinha> list() {
		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}

	@Transactional
	@Override
	public void delete(Cozinha cozinha) {
		manager.remove(findById(cozinha.getId()));
	}

}

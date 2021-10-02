package com.dev.devfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.devfood.domain.model.State;

public interface StateRepository extends JpaRepository<State, Long>{
	
}

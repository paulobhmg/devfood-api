package com.dev.devfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.devfood.domain.model.City;

public interface CityRepository extends JpaRepository<City, Long> {
	
}

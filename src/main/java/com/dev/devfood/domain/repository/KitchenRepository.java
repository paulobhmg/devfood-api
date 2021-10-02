package com.dev.devfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.devfood.domain.model.Kitchen;

public interface KitchenRepository extends JpaRepository<Kitchen, Long> {
	public Kitchen findByDescription(String name);
}

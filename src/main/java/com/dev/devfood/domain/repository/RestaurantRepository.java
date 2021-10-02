package com.dev.devfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.devfood.domain.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

}

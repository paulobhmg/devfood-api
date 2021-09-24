package com.dev.devfood.domain.repository;

import java.util.List;

public interface Repository<T> {
	T save(T t);
	T findById(Long id);
	List<T> list();
	void delete(T t);
}

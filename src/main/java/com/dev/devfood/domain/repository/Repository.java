package com.dev.devfood.domain.repository;

import java.util.List;

public interface Repository<T> {
	T salvar(T t);
	T buscarPorId(Long id);
	List<T> listar();
	void deletar(Long id);
}

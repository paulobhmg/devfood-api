package com.dev.devfood.domain.exception;

public class RecursoComoConstraintException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public RecursoComoConstraintException(String mensagem) {
		super(mensagem);
	}
}

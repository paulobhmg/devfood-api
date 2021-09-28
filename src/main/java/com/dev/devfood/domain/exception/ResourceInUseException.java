package com.dev.devfood.domain.exception;

public class ResourceInUseException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceInUseException(String mensagem) {
		super(mensagem);
	}
}

package com.sportyshoes.webservice.exceptions;

public class ShoeNotFoundException extends RuntimeException {
private static final long serialVersionUID = 1L;
	
	String message;

	public ShoeNotFoundException(String message) {
		super(message);
	}
	
}

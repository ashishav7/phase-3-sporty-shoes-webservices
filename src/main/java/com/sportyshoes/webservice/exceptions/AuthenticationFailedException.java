package com.sportyshoes.webservice.exceptions;

public class AuthenticationFailedException extends RuntimeException{
private static final long serialVersionUID = 1L;
	
	String message;

	public AuthenticationFailedException(String message) {
		super(message);
	}
}

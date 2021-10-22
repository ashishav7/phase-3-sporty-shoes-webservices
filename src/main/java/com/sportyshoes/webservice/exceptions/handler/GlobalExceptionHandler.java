package com.sportyshoes.webservice.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sportyshoes.webservice.exceptions.AuthenticationFailedException;
import com.sportyshoes.webservice.exceptions.ShoeNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	ExceptionResponse response;
	
	@ExceptionHandler(value=AuthenticationFailedException.class)
	public ResponseEntity<ExceptionResponse> unauthorizedException(AuthenticationFailedException exception){
		response = new ExceptionResponse(exception.getMessage(),new Date(), HttpStatus.METHOD_NOT_ALLOWED.name(), exception.getClass().getSimpleName());
		return new ResponseEntity<>(response,HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(value=ShoeNotFoundException.class)
	public ResponseEntity<ExceptionResponse> shoeNotFoundException(ShoeNotFoundException exception){
		response = new ExceptionResponse(exception.getMessage(),new Date(), HttpStatus.NOT_FOUND.name(), exception.getClass().getSimpleName());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
}

package com.restapi.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public ErrorApi handleException(ResourceNotFoundException ex) {
		var errorApi=new ErrorApi();
		errorApi.setDetails(ex.getMessage());
		errorApi.setTitle("Resource Not Found");
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorApi.setStatusCode(HttpStatus.BAD_GATEWAY.value());
		return errorApi;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorApi handleException(Exception ex) {
		var errorApi=new ErrorApi();
		errorApi.setDetails(ex.getMessage());
		errorApi.setTitle("Something went wrong");
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		errorApi.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return errorApi;
	}
	
}

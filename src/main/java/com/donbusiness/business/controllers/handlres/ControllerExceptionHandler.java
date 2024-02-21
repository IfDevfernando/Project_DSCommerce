package com.donbusiness.business.controllers.handlres;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.donbusiness.business.DTO.CustomError;
import com.donbusiness.business.DTO.ValidationError;
import com.donbusiness.business.services.exceptions.ResourceDataBaseException;
import com.donbusiness.business.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
	
	CustomError err = new CustomError(Instant.now(),status.value(),e.getMessage() , request.getRequestURI());
	
	return ResponseEntity.status(status).body(err);
	
	}
	
	@ExceptionHandler(ResourceDataBaseException.class)
	public ResponseEntity<CustomError> resourceDataBase(ResourceDataBaseException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
	
	CustomError err = new CustomError(Instant.now(),status.value(),e.getMessage() , request.getRequestURI());
	
	return ResponseEntity.status(status).body(err);
	
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomError> methodArgumentNotValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
	
		ValidationError err = new ValidationError(Instant.now(),status.value(),"invalid datas" , request.getRequestURI());
		e.getBindingResult().getFieldErrors();
		
		for(FieldError f: e.getBindingResult().getFieldErrors()) {
			err.addError(f.getField(), f.getDefaultMessage());
		}
		
		return ResponseEntity.status(status).body(err);
	
	}
}

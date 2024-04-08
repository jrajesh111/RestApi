package com.sathya.rest.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler
{
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handelValidationException(MethodArgumentNotValidException ex)
	{
		
	Map<String, String> errors =new HashMap<>();
			ex.getBindingResult()
			.getAllErrors()
		  .forEach(error ->{
			String fileName= ((FieldError) error).getField();
			
			String errorMassage =error.getDefaultMessage();
			errors.put(fileName,errorMassage);
			});
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.header("status","ratan bro")
				.body(errors);		
	}
}

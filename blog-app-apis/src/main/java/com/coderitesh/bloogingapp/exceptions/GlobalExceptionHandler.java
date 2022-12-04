package com.coderitesh.bloogingapp.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.coderitesh.bloogingapp.payloads.ApiResponce;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponce> resourceNotFoundHandler(ResourceNotFoundException ex){
		
		String message = ex.getMessage();
		
		ApiResponce apiResponce=new ApiResponce(message,false);
		
		return new ResponseEntity<ApiResponce>(apiResponce,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handlemethodArgsNotValidException(MethodArgumentNotValidException ex){
		
		Map<String,String> responce =new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldname =((FieldError)error).getField();
			String message=error.getDefaultMessage();
			
			responce.put(fieldname, message);
		});
		
		return new ResponseEntity<Map<String,String>>(responce,HttpStatus.BAD_REQUEST);
	}

}

package com.banking.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<String> handleBadCredentials(BadCredentialsException exception){
		return new ResponseEntity<String>("Invalid password, Please try again.", HttpStatus.UNAUTHORIZED);
	}
	
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<String> handleUserNotFound(UsernameNotFoundException exception){
		return 	new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneric(Exception ex ){
		return new ResponseEntity<String>("Something went wrong: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

}

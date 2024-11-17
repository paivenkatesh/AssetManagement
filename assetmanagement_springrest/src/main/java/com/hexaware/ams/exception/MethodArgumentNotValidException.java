package com.hexaware.ams.exception;

public class MethodArgumentNotValidException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public MethodArgumentNotValidException (String message) {
		super(message);

	}
}

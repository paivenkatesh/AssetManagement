package com.hexaware.ams.exception;

public class ResourceAlreadyExistsException extends RuntimeException  {

	
	private static final long serialVersionUID = 1L;
	
	public ResourceAlreadyExistsException (String message) {
		super(message);
	}

}

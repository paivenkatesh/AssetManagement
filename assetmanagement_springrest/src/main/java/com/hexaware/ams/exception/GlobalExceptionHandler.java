package com.hexaware.ams.exception;

/*
 * @Author: Arghya & Venkatesh
 * @Date: 08-11-2024
 * @Description: Global exception handler class that handles various exceptions thrown in the application. It provides custom responses for different types of exceptions, including resource not found, bad request, resource already exists, method argument validation errors, and unauthorized access.
 */
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> badRequestException(BadRequestException ex) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<String> resourceAlreadyExistsException(ResourceAlreadyExistsException ex ){
		String errorMessage = ex.getMessage();
		return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> MethodArgumentNotValidException(org.springframework.web.bind.MethodArgumentNotValidException ex){
		String errorMessage = ex.getMessage();
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<String> unauthorizedException(UnauthorizedException ex){
		String errorMessage = ex.getMessage();
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
	
}

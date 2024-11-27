package com.hexaware.ams.exception;
/*
 * @Author: Arghya Mandal
 * @Date: 08-11-2024
 * @Description: Custom exception class representing a resource not found error. It extends RuntimeException and includes a constructor that accepts a message to describe the error.
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

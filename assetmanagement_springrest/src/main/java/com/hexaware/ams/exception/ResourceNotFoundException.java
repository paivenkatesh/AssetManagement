package com.hexaware.ams.exception;
/*
@Author: Arghya Mandal
Date: 8-11-2024
*/
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

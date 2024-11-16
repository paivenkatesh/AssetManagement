package com.hexaware.ams.exception;
//Author: Arghya Mandal
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BadRequestException(String message) {
        super(message);
    }
}

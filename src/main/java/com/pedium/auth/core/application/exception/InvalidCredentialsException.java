package com.pedium.auth.core.application.exception;

public class InvalidCredentialsException extends RuntimeException {
    
    public InvalidCredentialsException() {}
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
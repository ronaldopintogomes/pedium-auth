package com.pedium.auth.domain.exception;

public class InvalidCredentialsException extends RuntimeException {
    
    public InvalidCredentialsException() {}
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
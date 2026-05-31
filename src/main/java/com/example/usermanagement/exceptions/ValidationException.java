package com.example.usermanagement.exceptions;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}

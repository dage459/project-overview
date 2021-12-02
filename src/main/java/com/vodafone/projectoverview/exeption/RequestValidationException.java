package com.vodafone.projectoverview.exeption;

public class RequestValidationException extends RuntimeException{

    public RequestValidationException(final String message) {
        super(message);
    }
}

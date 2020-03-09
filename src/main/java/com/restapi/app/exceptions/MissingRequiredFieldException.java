package com.restapi.app.exceptions;

public class MissingRequiredFieldException extends RuntimeException {
    private static final long serialVersionUID = 6778016226618597752L;

    public MissingRequiredFieldException(String message) {
        super(message);
    }
}

package com.restapi.app.exceptions;

public class RecordNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -1583997564255288876L;

    public RecordNotFoundException(String message) {
        super(message);
    }
}

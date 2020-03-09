package com.restapi.app.exceptions;

public class CouldNotDeleteRecordException extends RuntimeException {
    private static final long serialVersionUID = -5102369300012685759L;

    public CouldNotDeleteRecordException(String message) {
        super(message);
    }
}
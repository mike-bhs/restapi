package com.restapi.app.exceptions;

public class CouldNotCreateRecordException extends RuntimeException {
    private static final long serialVersionUID = -3429852058445284222L;

    public CouldNotCreateRecordException(String message) {
        super(message);
    }
}

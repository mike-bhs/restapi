package com.restapi.app.exceptions;

public class CouldNotUpdateRecordException extends RuntimeException {
    private static final long serialVersionUID = 8315034405321113681L;

    public CouldNotUpdateRecordException(String message) {
        super(message);
    }
}

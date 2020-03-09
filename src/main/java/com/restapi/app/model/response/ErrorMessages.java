package com.restapi.app.model.response;

public enum ErrorMessages {
    MISSING_REQUIRED_FIELD("Missing required field"),
    RECORD_ALREADY_EXISTS("Record already exists"),
    RECORD_NOT_FOUND("Record not found"),
    COULD_NOT_UPDATE_RECORD("Could not update record"),
    COULD_NOT_DELETE_RECORD("Could not delete record"),
    INTERNAL_SERVER_ERROR("Internal server error");

    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.setErrorMessage(errorMessage);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

package com.restapi.app.exceptions;

import com.restapi.app.model.response.ErrorMessage;
import com.restapi.app.model.response.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CouldNotCreateRecordExceptionMapper implements ExceptionMapper<CouldNotCreateRecordException> {
    @Override
    public Response toResponse(CouldNotCreateRecordException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), ErrorMessages.RECORD_ALREADY_EXISTS.name());

        return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
    }
}
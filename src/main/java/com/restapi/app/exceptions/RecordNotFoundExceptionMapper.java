package com.restapi.app.exceptions;

import com.restapi.app.model.response.ErrorMessage;
import com.restapi.app.model.response.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RecordNotFoundExceptionMapper implements ExceptionMapper<RecordNotFoundException> {
    @Override
    public Response toResponse(RecordNotFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), ErrorMessages.RECORD_NOT_FOUND.name());

        return Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
    }
}
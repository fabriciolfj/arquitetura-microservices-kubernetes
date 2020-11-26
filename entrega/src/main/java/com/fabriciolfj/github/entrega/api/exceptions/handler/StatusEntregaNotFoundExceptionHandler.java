package com.fabriciolfj.github.entrega.api.exceptions.handler;

import com.fabriciolfj.github.entrega.api.exceptions.StatusEntregaNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class StatusEntregaNotFoundExceptionHandler implements ExceptionMapper<StatusEntregaNotFoundException> {

    @Override
    public Response toResponse(StatusEntregaNotFoundException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}

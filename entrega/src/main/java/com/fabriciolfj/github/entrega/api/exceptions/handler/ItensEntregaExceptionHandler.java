package com.fabriciolfj.github.entrega.api.exceptions.handler;

import com.fabriciolfj.github.entrega.api.exceptions.ItensEntregaException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ItensEntregaExceptionHandler implements ExceptionMapper<ItensEntregaException> {

    @Override
    public Response toResponse(ItensEntregaException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}

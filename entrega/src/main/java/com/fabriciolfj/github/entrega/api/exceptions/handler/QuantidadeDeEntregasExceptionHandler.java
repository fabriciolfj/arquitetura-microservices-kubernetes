package com.fabriciolfj.github.entrega.api.exceptions.handler;

import com.fabriciolfj.github.entrega.api.exceptions.QuantidadeDeEntregasException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class QuantidadeDeEntregasExceptionHandler implements ExceptionMapper<QuantidadeDeEntregasException> {
    @Override
    public Response toResponse(QuantidadeDeEntregasException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}

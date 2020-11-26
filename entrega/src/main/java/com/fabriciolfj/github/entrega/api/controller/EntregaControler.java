package com.fabriciolfj.github.entrega.api.controller;

import com.fabriciolfj.github.entrega.api.dto.request.EntregaRequest;
import com.fabriciolfj.github.entrega.domain.service.EntregaService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("entregas")
@Consumes("application/json")
@Produces("application/json")
public class EntregaControler {

    @Inject
    private EntregaService entregaService;

    @POST
    public Response create(final EntregaRequest request) {
        return Response.ok(entregaService.save(request)).status(201).build();
    }

    @POST
    @Path(("/saida"))
    public Response createExit(final EntregaRequest request) {
        return Response.ok(entregaService.saveExit(request)).status(202).build();
    }
}

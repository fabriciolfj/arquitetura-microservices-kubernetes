package com.fabriciolfj.github.entrega.domain.integration;

import com.fabriciolfj.github.entrega.domain.entity.Entrega;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;

@RequestScoped
public class EntregaIntegration {

    @Inject
    @Channel("entrega")
    Emitter<String> emitter;

    public void process(final Entrega entregaValida) {
        var json = JsonbBuilder.create();
        emitter.send(json.toJson(entregaValida));
    }
}

package com.fabriciolfj.github.entrega.domain.integration;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class EntregaIntegration {

    @Inject
    @Channel("entrega")
    Emitter<String> emitter;

    public void process(final String entrega) {
        emitter.send(entrega);
    }
}

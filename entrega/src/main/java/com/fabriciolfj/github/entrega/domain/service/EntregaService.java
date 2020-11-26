package com.fabriciolfj.github.entrega.domain.service;

import com.fabriciolfj.github.entrega.api.dto.request.EntregaRequest;
import com.fabriciolfj.github.entrega.api.dto.response.EntregaResponse;
import com.fabriciolfj.github.entrega.api.dto.response.SaidaResponse;
import com.fabriciolfj.github.entrega.domain.entity.Entrega;
import com.fabriciolfj.github.entrega.domain.entity.enuns.StatusEntrega;
import com.fabriciolfj.github.entrega.domain.facade.create.EntregaCreate;
import com.fabriciolfj.github.entrega.domain.facade.validation.SaidaEntregaValidation;
import com.fabriciolfj.github.entrega.domain.integration.EntregaIntegration;
import com.fabriciolfj.github.entrega.domain.repository.EntregaRepository;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Slf4j
@ApplicationScoped
public class EntregaService {

    @Inject
    private EntregaCreate create;

    @Inject
    private EntregaRepository entregaRepository;

    @Inject
    private SaidaEntregaValidation validation;

    @Inject
    private EntregaIntegration entregaIntegration;

    @Transactional(value = Transactional.TxType.REQUIRED)
    public EntregaResponse save(final EntregaRequest request) {
        var entrega = create.create(request);
        entrega.persistAndFlush();
        return EntregaResponse
                .builder()
                .data(LocalDate.now())
                .build();
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public SaidaResponse saveExit(final EntregaRequest request) {
        var entrega = create.create(request);
        var entregas = entregaRepository.findEntregasRecebidas(request.documento);
        var entregaValida = validation.validarSaida(entregas, entrega);

        var json = JsonbBuilder.create();
        json.toJson(entrega);
        entregaIntegration.process(json.toJson(entregaValida));
        return SaidaResponse
                .builder()
                .dataExit(LocalDate.now())
                .build();
    }

    private void atualizarEntrega(final Entrega entrega) {
        entrega.statusEntrega = StatusEntrega.ENTREGUE;
        entrega.persistAndFlush();
    }
}

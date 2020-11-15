package com.fabriciolfj.github.entrega.domain.service;

import com.fabriciolfj.github.entrega.api.dto.request.EntregaRequest;
import com.fabriciolfj.github.entrega.api.dto.response.EntregaResponse;
import com.fabriciolfj.github.entrega.api.mapper.EntregaMapper;
import com.fabriciolfj.github.entrega.api.mapper.ItemMapper;
import com.fabriciolfj.github.entrega.domain.entity.Entrega;
import com.fabriciolfj.github.entrega.domain.entity.Item;
import com.fabriciolfj.github.entrega.domain.entity.enuns.StatusEntrega;
import com.fabriciolfj.github.entrega.domain.facade.create.EntregaCreate;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Slf4j
@ApplicationScoped
public class EntregaService {

    @Inject
    private EntregaCreate create;

    @Transactional(value = Transactional.TxType.REQUIRED)
    public EntregaResponse save(final EntregaRequest request) {
        var entrega = create.create(request);
        entrega.persist();
        return EntregaResponse
                .builder()
                .data(LocalDate.now())
                .build();
    }
}

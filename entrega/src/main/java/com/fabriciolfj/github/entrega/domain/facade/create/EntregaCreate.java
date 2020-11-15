package com.fabriciolfj.github.entrega.domain.facade.create;

import com.fabriciolfj.github.entrega.api.dto.request.EntregaRequest;
import com.fabriciolfj.github.entrega.api.mapper.EntregaMapper;
import com.fabriciolfj.github.entrega.api.mapper.ItemMapper;
import com.fabriciolfj.github.entrega.domain.entity.Entrega;
import com.fabriciolfj.github.entrega.domain.entity.Item;
import com.fabriciolfj.github.entrega.domain.entity.enuns.StatusEntrega;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class EntregaCreate {

    @Inject
    private EntregaMapper mapper;
    @Inject
    private ItemMapper itemMapper;

    public Entrega create(final EntregaRequest request) {
        var entrega = getEntregaRecebida(request);
        entrega.itens = getItems(request, entrega);
        return entrega;
    }

    private List<Item> getItems(EntregaRequest request, Entrega entrega) {
        return request.itens.stream().map(item -> {
            var i = itemMapper.toEntity(item);
            i.entrega = entrega;
            return i;
        }).collect(Collectors.toList());
    }

    private Entrega getEntregaRecebida(EntregaRequest request) {
        var entrega = mapper.toEntity(request);
        entrega.statusEntrega = StatusEntrega.RECEBIDA;
        return entrega;
    }
}

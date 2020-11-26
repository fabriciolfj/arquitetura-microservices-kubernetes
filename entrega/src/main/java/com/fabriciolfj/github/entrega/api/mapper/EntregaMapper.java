package com.fabriciolfj.github.entrega.api.mapper;

import com.fabriciolfj.github.entrega.api.dto.request.EntregaRequest;
import com.fabriciolfj.github.entrega.domain.entity.Entrega;
import com.fabriciolfj.github.entrega.domain.entity.enuns.StatusEntrega;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;

@Mapper(componentModel = "cdi")
public interface EntregaMapper {

    @Mapping(target = "documento", source = "request.documento")
    @Mapping(target = "cliente", source = "request.cliente")
    @Mapping(target = "destino", source = "request.destino")
    @Mapping(target = "itens", source = "request.itens", ignore = true)
    @Mapping(target = "order", source = "request.order")
    @ValueMapping(target = "statusEntrega", source = "RECEBIDA")
    Entrega toEntity(final EntregaRequest request, final StatusEntrega statusEntrega);
}

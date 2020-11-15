package com.fabriciolfj.github.entrega.api.mapper;

import com.fabriciolfj.github.entrega.api.dto.request.EntregaRequest;
import com.fabriciolfj.github.entrega.domain.entity.Entrega;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface EntregaMapper {

    @Mapping(target = "documento", source = "documento")
    @Mapping(target = "cliente", source = "cliente")
    @Mapping(target = "destino", source = "destino")
    @Mapping(target = "itens", source = "itens", ignore = true)
    Entrega toEntity(final EntregaRequest request);
}

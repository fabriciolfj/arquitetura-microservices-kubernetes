package com.fabriciolfj.github.entrega.api.mapper;

import com.fabriciolfj.github.entrega.api.dto.request.ItemRequest;
import com.fabriciolfj.github.entrega.domain.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface ItemMapper {

    @Mapping(target = "code", source = "code")
    @Mapping(target = "valor", source = "preco")
    @Mapping(target = "quantidade", source = "quantidade")
    Item toEntity(final ItemRequest itemRequest);
}

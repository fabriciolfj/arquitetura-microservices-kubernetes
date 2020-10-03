package com.github.fabriciolfj.inventario.api.mapper;

import com.github.fabriciolfj.inventario.api.dto.request.ProductCreateRequest;
import com.github.fabriciolfj.inventario.domain.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductRequestMapper {

    @Mapping(source = "code", target = "code")
    Product toEntity(final ProductCreateRequest request);
}

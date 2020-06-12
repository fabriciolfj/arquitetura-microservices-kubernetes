package com.github.fabriciolfj.product.api.mapper;

import com.github.fabriciolfj.product.api.dto.request.ProductRequestDTO;
import com.github.fabriciolfj.product.domain.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapperRequest {

    Product toDomain(final ProductRequestDTO dt);
}

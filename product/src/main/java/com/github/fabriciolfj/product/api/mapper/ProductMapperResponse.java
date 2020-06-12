package com.github.fabriciolfj.product.api.mapper;

import com.github.fabriciolfj.product.api.dto.response.ProductResponseDTO;
import com.github.fabriciolfj.product.domain.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapperResponse {

    ProductResponseDTO toDTO(final Product product);
}

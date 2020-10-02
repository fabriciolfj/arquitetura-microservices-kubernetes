package com.github.fabriciolfj.customer.api.mapper;

import com.github.fabriciolfj.customer.api.dto.CustomerDTO;
import com.github.fabriciolfj.customer.api.mapper.decorated.CustomerMapperDecorated;
import com.github.fabriciolfj.customer.domain.entity.Customer;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@DecoratedWith(CustomerMapperDecorated.class)
public interface CustomerMapper {

    @Mapping(target = "name", source = "name")
    Customer toEntity(final CustomerDTO dto);
}

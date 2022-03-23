package com.github.fabriciolfj.inventario.api.mapper;

import com.github.fabriciolfj.inventario.api.dto.request.OrderRequest;
import com.github.fabriciolfj.inventario.api.dto.request.OrderStatusRequest;
import com.github.fabriciolfj.inventario.api.mapper.decorated.OrderStatusMapperDecorated;
import com.github.fabriciolfj.inventario.domain.entity.enuns.StatusOrder;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@DecoratedWith(OrderStatusMapperDecorated.class)
public interface OrderStatusMapper {

    OrderStatusRequest toRequest(final OrderRequest orderRequest, final StatusOrder status);
}

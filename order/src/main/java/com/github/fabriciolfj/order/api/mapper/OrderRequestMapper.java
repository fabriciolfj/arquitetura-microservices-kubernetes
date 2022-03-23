package com.github.fabriciolfj.order.api.mapper;

import com.github.fabriciolfj.order.api.dto.request.OrderRequest;
import com.github.fabriciolfj.order.api.mapper.decorated.OrderRequestMapperDecorated;
import com.github.fabriciolfj.order.domain.entity.Order;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@DecoratedWith(OrderRequestMapperDecorated.class)
public interface OrderRequestMapper {

    Order toOrder(final OrderRequest request);
}

package com.github.fabriciolfj.order.api.mapper;

import com.github.fabriciolfj.order.api.dto.response.OrderResponse;
import com.github.fabriciolfj.order.api.mapper.decorated.OrderResponseDecorated;
import com.github.fabriciolfj.order.domain.entity.Order;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@DecoratedWith(OrderResponseDecorated.class)
public interface OrderResponseMapper {

    OrderResponse toResponse(final Order order);
}

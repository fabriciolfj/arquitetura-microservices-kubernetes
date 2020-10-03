package com.github.fabriciolfj.order.api.mapper.decorated;

import com.github.fabriciolfj.order.api.dto.response.ItemResponse;
import com.github.fabriciolfj.order.api.dto.response.OrderResponse;
import com.github.fabriciolfj.order.api.mapper.OrderResponseMapper;
import com.github.fabriciolfj.order.domain.entity.Item;
import com.github.fabriciolfj.order.domain.entity.Order;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public abstract class OrderResponseDecorated implements OrderResponseMapper {

    @Override
    public OrderResponse toResponse(Order order) {
        return OrderResponse
                .builder()
                .descricao(order.getDescricao())
                .items(toItems(order.getItems()))
                .customer(order.getCustomer())
                .total(order.getTotal())
                .dataEmissao(order.getDataEmissao())
                .code(order.getCode())
                .build();
    }

    private List<ItemResponse> toItems(final List<Item> items) {
        return items.stream()
                .map(i -> ItemResponse.builder()
                        .code(i.getCode())
                        .price(i.getPrice())
                        .quantity(i.getQuantity())
                        .total(i.getTotal())
                        .build())
                .collect(Collectors.toList());
    }
}

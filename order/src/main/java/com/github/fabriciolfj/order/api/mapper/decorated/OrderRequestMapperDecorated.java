package com.github.fabriciolfj.order.api.mapper.decorated;

import com.github.fabriciolfj.order.api.dto.request.OrderRequest;
import com.github.fabriciolfj.order.api.mapper.OrderRequestMapper;
import com.github.fabriciolfj.order.domain.entity.Item;
import com.github.fabriciolfj.order.domain.entity.Order;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public abstract class OrderRequestMapperDecorated implements OrderRequestMapper {

    @Override
    public Order toOrder(final OrderRequest request) {
        var order = Order.builder()
                .descricao(request.getDescricao())
                .customer(request.getCustomer())
                .dataEmissao(LocalDate.now())
                .build();

        order.setItems(toItems(request, order));
        return order;
    }

    private List<Item> toItems(final OrderRequest request, final Order order) {
        return request.getItems().stream()
                .map(i -> Item.builder().order(order).code(i.getCode()).quantity(i.getQuantity()).build())
                .collect(Collectors.toList());
    }
}

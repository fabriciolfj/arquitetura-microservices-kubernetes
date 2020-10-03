package com.github.fabriciolfj.order.domain.facade.create;


import com.github.fabriciolfj.order.api.dto.request.OrderRequest;
import com.github.fabriciolfj.order.api.exceptions.CreateOrderException;
import com.github.fabriciolfj.order.api.mapper.OrderRequestMapper;
import com.github.fabriciolfj.order.domain.command.OrderCommandCreated;
import com.github.fabriciolfj.order.domain.entity.Item;
import com.github.fabriciolfj.order.domain.entity.Order;
import com.github.fabriciolfj.order.domain.facade.fetcher.ProductFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.of;

@Component
@RequiredArgsConstructor
public class OrderCreate {

    private final ProductFetcher productFetcher;
    private final OrderRequestMapper mapper;
    private final OrderCommandCreated orderCommandCreated;

    public Order create(final OrderRequest request) {
        return of(mapper.toOrder(request))
                .map(order -> {
                    updatePriceItems(order.getItems());
                    return orderCommandCreated.execute(order);
                }).orElseThrow(() -> new CreateOrderException(request));
    }

    private List<Item> updatePriceItems(final List<Item> items) {
        return items.stream()
                .map(item -> {
                    var product = productFetcher.get(item.getCode());
                    item.setPrice(product.getPrice());
                    return item;
                }).collect(Collectors.toList());
    }

}

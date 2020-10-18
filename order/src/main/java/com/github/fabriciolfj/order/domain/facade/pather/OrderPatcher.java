package com.github.fabriciolfj.order.domain.facade.pather;

import com.github.fabriciolfj.order.domain.entity.Order;
import com.github.fabriciolfj.order.domain.entity.enuns.Status;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class OrderPatcher {

    public Order execute(final Order order) {
        return updateData(order);
    }

    private Order updateData(final Order order) {
        order.setCode(UUID.randomUUID().toString());
        order.setStatus(Status.CRIADO);
        return calcularItem(order);
    }

    private Order calcularItem(final Order order) {
        order.getItems().forEach(i -> i.calcularTotal());
        return calcularTotalOrder(order);
    }

    private Order calcularTotalOrder(final Order order) {
        var total = order.getItems()
                .stream()
                .map(item -> item.getTotal())
                .reduce((x, y) -> x.add(y)).orElse(BigDecimal.ZERO);

        order.setTotal(total);
        return order;
    }
}

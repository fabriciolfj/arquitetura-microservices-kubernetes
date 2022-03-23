package com.github.fabriciolfj.order.api.exceptions;

import com.github.fabriciolfj.order.api.dto.request.OrderRequest;

public class CreateOrderException extends RuntimeException {

    public CreateOrderException(final OrderRequest request) {
        super("Falha ao atualizar os dados do pedido: " + request);
    }
}

package com.github.fabriciolfj.order.api.exceptions;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(final String code) {
        super("Order not found: " + code);
    }
}

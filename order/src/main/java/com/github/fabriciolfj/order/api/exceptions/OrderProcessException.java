package com.github.fabriciolfj.order.api.exceptions;

public class OrderProcessException extends RuntimeException {

    public OrderProcessException() {
        super("Fail process order");
    }
}

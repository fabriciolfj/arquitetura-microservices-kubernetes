package com.github.fabriciolfj.order.api.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(final String code) {
        super("Customer not found. Code informed: " + code);
    }
}

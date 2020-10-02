package com.github.fabriciolfj.customer.api.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String code) {
        super("Customer não encontrado: " + code);
    }
}

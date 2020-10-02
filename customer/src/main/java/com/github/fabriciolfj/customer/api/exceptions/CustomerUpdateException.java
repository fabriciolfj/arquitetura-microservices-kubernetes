package com.github.fabriciolfj.customer.api.exceptions;

public class CustomerUpdateException extends RuntimeException {

    public CustomerUpdateException(String code) {
        super("Fail update customer: " + code);
    }
}

package com.github.fabriciolfj.order.api.exceptions;

public class ProductClientFailException extends RuntimeException {

    public ProductClientFailException(final String code) {
        super("Product not found, code : " + code);
    }
}

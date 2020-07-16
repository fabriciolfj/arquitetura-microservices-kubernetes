package com.github.fabriciolfj.product.api.exceptions;

public class ProductCreateException extends RuntimeException {

    public ProductCreateException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public ProductCreateException(final String msg) {
        super(msg);
    }
}

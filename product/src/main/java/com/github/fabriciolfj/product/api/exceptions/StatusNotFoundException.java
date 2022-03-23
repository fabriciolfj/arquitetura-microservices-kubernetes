package com.github.fabriciolfj.product.api.exceptions;

public class StatusNotFoundException extends RuntimeException {

    public StatusNotFoundException(final String msg) {
        super(msg);
    }
}

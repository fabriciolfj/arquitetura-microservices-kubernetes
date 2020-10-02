package com.github.fabriciolfj.customer.api.exceptions;

import com.github.fabriciolfj.customer.api.dto.CustomerDTO;

public class CustomerCreateException extends RuntimeException {

    public CustomerCreateException(final String msg) {
        super(msg);
    }

    public CustomerCreateException(final CustomerDTO dto) {
        super("Falha ao criar o customer: " + dto);
    }

}

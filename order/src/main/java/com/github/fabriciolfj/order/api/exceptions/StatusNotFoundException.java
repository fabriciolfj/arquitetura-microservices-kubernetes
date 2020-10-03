package com.github.fabriciolfj.order.api.exceptions;

public class StatusNotFoundException extends RuntimeException {

    public StatusNotFoundException(final String descricao) {
        super("Status não encontrado. Status informado: " + descricao);
    }
}

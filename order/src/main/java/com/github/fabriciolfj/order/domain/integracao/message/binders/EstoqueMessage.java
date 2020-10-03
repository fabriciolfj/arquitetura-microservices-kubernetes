package com.github.fabriciolfj.order.domain.integracao.message.binders;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EstoqueMessage {
    String OUTPUT = "topic-order-estoque";

    @Output(OUTPUT)
    MessageChannel estoqueOutput();

}
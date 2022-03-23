package com.github.fabriciolfj.order.domain.integracao.message.binders;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface StatusMessage {

    String INPUT = "topic-order-status";

    @Input(INPUT)
    SubscribableChannel input();
}

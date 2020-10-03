package com.github.fabriciolfj.inventario.domain.integration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface OrderBinder {
    String INPUT = "input-estoque";

    @Input(INPUT)
    SubscribableChannel input();
}

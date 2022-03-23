package com.github.fabriciolfj.inventario.domain.integration;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface StatusBinder {

    String OUTPUT = "output-status";

    @Output(OUTPUT)
    MessageChannel output();
}

package com.github.fabriciolfj.inventario.domain.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.inventario.api.dto.request.OrderRequest;
import com.github.fabriciolfj.inventario.api.mapper.OrderStatusMapper;
import com.github.fabriciolfj.inventario.domain.entity.enuns.StatusOrder;
import com.github.fabriciolfj.inventario.domain.integration.StatusBinder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderProducer {

    private final StatusBinder statusBinder;
    private final ObjectMapper objectMapper;
    private final OrderStatusMapper mapper;

    public synchronized void send(final OrderRequest request, final StatusOrder statusOrder) {
        try {
            log.info("Envio das informações para o pedido.");
            var value = mapper.toRequest(request, statusOrder);
            var json = objectMapper.writeValueAsString(value);
            var message = MessageBuilder.withPayload(json)
                    .setHeader(KafkaHeaders.TOPIC, "topic-order-status")
                    .build();

            statusBinder.output().send(message);
        } catch (Exception e) {
            log.error("Fail send topic status order. Details: {}", e.getMessage());
        }
    }

}

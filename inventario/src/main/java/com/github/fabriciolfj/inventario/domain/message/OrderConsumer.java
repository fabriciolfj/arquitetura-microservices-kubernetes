package com.github.fabriciolfj.inventario.domain.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.inventario.api.dto.request.OrderRequest;
import com.github.fabriciolfj.inventario.domain.facade.patcher.InventarioPatcher;
import com.github.fabriciolfj.inventario.domain.integration.OrderBinder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderConsumer {

    private final ObjectMapper objectMapper;
    private final InventarioPatcher inventarioPatcher;

    @StreamListener(OrderBinder.INPUT)
    public void onConsumer(final String message) {
        try {
            log.info("Message consumer: {}", message);
            var order = objectMapper.readValue(message, OrderRequest.class);
            inventarioPatcher.process(order)
            .subscribe();
        } catch (Exception e) {
            log.error("Fail consumer message: {}", e.getMessage());
        }
    }
}

package com.github.fabriciolfj.inventario.domain.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderConsumer {

    @KafkaListener(topics = "${app.topic}")
    public void onConsumer(final String message, final Acknowledgment acknowledgment) {
        try {
            log.info("Message consumer: {}", message);
            acknowledgment.acknowledge();
        } catch (Exception e) {
            log.error("Fail consumer message: {}", e.getMessage());
        }
    }
}

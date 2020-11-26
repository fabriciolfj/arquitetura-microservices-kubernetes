package com.github.fabriciolfj.order.domain.integracao.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.order.api.dto.request.EntregaRequest;
import com.github.fabriciolfj.order.domain.integracao.message.binders.EntregaMessage;
import com.github.fabriciolfj.order.domain.integracao.message.binders.StatusMessage;
import com.github.fabriciolfj.order.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ConsumerStatusOrder {

    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    @StreamListener(StatusMessage.INPUT)
    public void consumer(final String message) {
        log.info("Mensagem sendo consumida pelo topic status: " + message);
    }

    @StreamListener(EntregaMessage.INPUT)
    public void consumerEntrega(final Message<?> message) throws JsonProcessingException {
        var ack = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);

        if (ack != null) {
            ack.acknowledge();
        }

        log.info("Entrega realizada: {}", message.getPayload());
        /*var entrega = objectMapper.readValue(message, EntregaRequest.class);
        orderService.update(entrega.getOrder());*/
    }
}

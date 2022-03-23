package com.github.fabriciolfj.order.domain.integracao.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.order.api.dto.response.OrderResponse;
import com.github.fabriciolfj.order.domain.integracao.message.binders.EstoqueMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class EstoqueIntegration {

    private final ObjectMapper mapper;
    private final EstoqueMessage estoqueMessage;

    public void send(final OrderResponse orderResponse) {
        try {
            var json = mapper.writeValueAsString(orderResponse);
            var message = MessageBuilder.withPayload(json).build();

            log.info("Json sendo enviado para o topic: {}", json);
            estoqueMessage.estoqueOutput().send(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

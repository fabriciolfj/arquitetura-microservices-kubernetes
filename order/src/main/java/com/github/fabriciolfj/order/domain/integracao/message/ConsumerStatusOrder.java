package com.github.fabriciolfj.order.domain.integracao.message;

import com.github.fabriciolfj.order.domain.integracao.message.binders.StatusMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ConsumerStatusOrder {

    @StreamListener(StatusMessage.INPUT)
    public void consumer(final String message) {
        log.info("Mensagem sendo consumida pelo topic status: " + message);
    }
}

package com.github.fabriciolfj.order.domain.facade.fetcher;

import com.github.fabriciolfj.order.api.exceptions.CustomerNotFoundException;
import com.github.fabriciolfj.order.domain.integracao.http.CustomerClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomerFetcher {

    private final CustomerClient client;

    public void existsCustomer(final String code) {
        try {
            client.get(code);
        } catch (Exception e) {
            log.error("Fail query customer. Details:  {}", e.getMessage());
            throw new CustomerNotFoundException(code);
        }
    }
}

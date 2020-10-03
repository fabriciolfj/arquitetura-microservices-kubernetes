package com.github.fabriciolfj.order.domain.facade.fetcher;

import com.github.fabriciolfj.order.api.dto.response.ProductResponse;
import com.github.fabriciolfj.order.api.exceptions.ProductClientFailException;
import com.github.fabriciolfj.order.domain.integracao.http.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductFetcher {

    private final ProductClient client;

    public ProductResponse get(final String code) {
        try {
            return client.getProduct(code);
        } catch (Exception e) {
            throw new ProductClientFailException(code);
        }
    }
}

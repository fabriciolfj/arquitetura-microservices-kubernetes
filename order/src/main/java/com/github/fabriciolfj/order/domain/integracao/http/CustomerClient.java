package com.github.fabriciolfj.order.domain.integracao.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer", url = "http://localhost:9290", decode404 = false)
public interface CustomerClient {

    @GetMapping("/customers/{code}")
    void get(@PathVariable("code") final String code);
}

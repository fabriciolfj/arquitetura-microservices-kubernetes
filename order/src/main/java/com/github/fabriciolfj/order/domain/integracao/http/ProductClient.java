package com.github.fabriciolfj.order.domain.integracao.http;

import com.github.fabriciolfj.order.api.dto.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(decode404 = false, url = "http://localhost:9090", name = "product")
public interface ProductClient {

    @GetMapping("/products/{code}")
    ProductResponse getProduct(@PathVariable("code") final String code);
}

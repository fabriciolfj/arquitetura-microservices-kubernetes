package com.github.fabriciolfj.product.api.controller;

import com.github.fabriciolfj.product.api.dto.request.ProductRequestDTO;
import com.github.fabriciolfj.product.api.dto.response.ProductResponseDTO;
import com.github.fabriciolfj.product.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    @GetMapping("/{code}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<ProductResponseDTO> findByCode(@PathVariable("code") final String code) {
        return service.findByCode(code);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Flux<ProductResponseDTO> findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductResponseDTO> create(@Valid @RequestBody ProductRequestDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{code}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<ProductResponseDTO> update(@Valid @RequestBody ProductRequestDTO dto, @PathVariable("code") final String code) {
        return service.update(dto, code);
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> delete(@PathVariable("code") final String code) {
        return service.deleteByCode(code);
    }

    @PutMapping("/{code}/{status}")
    public Mono<?> updateStatus(@PathVariable("code") final String code, @PathVariable("status") final String status) {
        return service.updateStatus(code, status);
    }

}

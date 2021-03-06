package com.github.fabriciolfj.product.domain.service;

import com.github.fabriciolfj.product.api.dto.request.ProductRequestDTO;
import com.github.fabriciolfj.product.api.dto.response.ProductResponseDTO;
import com.github.fabriciolfj.product.api.exceptions.ProductCreateException;
import com.github.fabriciolfj.product.api.exceptions.ProductNotFoundException;
import com.github.fabriciolfj.product.api.exceptions.ProductUpdateException;
import com.github.fabriciolfj.product.api.mapper.ProductMapperRequest;
import com.github.fabriciolfj.product.api.mapper.ProductMapperResponse;
import com.github.fabriciolfj.product.domain.model.enuns.Status;
import com.github.fabriciolfj.product.domain.repository.ProductRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapperRequest request;
    private final ProductMapperResponse response;

    public Mono<ProductResponseDTO> findByCode(final String code) {
        return repository.findByCode(code)
                .flatMap(result -> Mono.just(response.toDTO(result)))
                .switchIfEmpty(Mono.error(new ProductNotFoundException("Product not found")));
    }

    public Mono<Void> deleteByCode(final String code) {
        return repository.findByCode(code)
                .flatMap(result -> repository.delete(result));
    }

    @CircuitBreaker(name = "list", fallbackMethod = "fallbackFindAll")
    public Flux<ProductResponseDTO> findAll() {
        return repository.findAll().flatMap(p -> Flux.just(response.toDTO(p)));
    }

    public Flux<ProductResponseDTO> fallbackFindAll(Throwable t) {
        log.info("Fallback started");
        return Flux.empty();
    }

    public Mono<?> updateStatus(final String code, final String value) {
        return repository.findByCode(code)
                .flatMap(p -> {
                    var status = Status.toEnum(value);
                    p.setStatus(status.getDescription());
                    return repository.save(p);
                }).flatMap(r -> Mono.empty())
                .onErrorResume(e -> Mono.error(new ProductUpdateException("Fail update product. Detalhes " + e.getMessage())));
    }

    public Mono<ProductResponseDTO> create(final ProductRequestDTO dto) {
        return Mono.just(dto)
                .map(d -> request.toDomain(d))
                .flatMap(p -> {
                    p.setCode(UUID.randomUUID().toString());
                    return repository.save(p);
                })
                .flatMap(result -> Mono.just(response.toDTO(result)))
                .onErrorResume(e -> Mono.error(new ProductCreateException("Fail create product. Details: " + e.getMessage())));
    }

    public Mono<ProductResponseDTO> update(final ProductRequestDTO dto, final String code) {
        return repository.findByCode(code)
                .onErrorResume(e -> Mono.error(new RuntimeException()))
                .flatMap(result -> {
                    var product = request.toDomain(dto);
                    BeanUtils.copyProperties(product, result, "code", "id");
                    return repository.save(result);
                })
                .flatMap(p -> Mono.just(response.toDTO(p)));
    }


}
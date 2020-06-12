package com.github.fabriciolfj.product.domain.service;

import com.github.fabriciolfj.product.api.dto.request.ProductRequestDTO;
import com.github.fabriciolfj.product.api.dto.response.ProductResponseDTO;
import com.github.fabriciolfj.product.api.mapper.ProductMapperRequest;
import com.github.fabriciolfj.product.api.mapper.ProductMapperResponse;
import com.github.fabriciolfj.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapperRequest request;
    private final ProductMapperResponse response;

    public Mono<ProductResponseDTO> findByCode(final String code) {
        return repository.findByCode(code)
                .flatMap(result -> Mono.just(response.toDTO(result)));
    }

    public Mono<Void> deleteByCode(final String code) {
        return repository.findByCode(code)
                .flatMap(result -> repository.delete(result));
    }

    public Flux<ProductResponseDTO> findAll() {
        return repository.findAll().flatMap(p -> Flux.just(response.toDTO(p)));
    }

    public Mono<ProductResponseDTO> create(final ProductRequestDTO dto) {
        return Mono.just(dto)
                .map(d -> request.toDomain(d))
                .flatMap(p -> {
                    p.setCode(UUID.randomUUID().toString());
                    return repository.save(p);
                })
                .flatMap(result -> Mono.just(response.toDTO(result)));
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
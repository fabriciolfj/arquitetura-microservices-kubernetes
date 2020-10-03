package com.github.fabriciolfj.inventario.domain.service;

import com.github.fabriciolfj.inventario.api.dto.request.ProductCreateRequest;
import com.github.fabriciolfj.inventario.api.exceptions.DomainBusinessException;
import com.github.fabriciolfj.inventario.api.mapper.ProductRequestMapper;
import com.github.fabriciolfj.inventario.domain.entity.Product;
import com.github.fabriciolfj.inventario.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final ProductRepository repository;
    private final ProductRequestMapper requestMapper;

    public Flux<Product> findAll() {
        return repository.findAll();
    }

    public Mono<?> exitQuantity(final String code, final Integer quantity) {
        return repository.findByCode(code)
                .flatMap(product -> {
                    product.removeQuantity(quantity);
                    return repository.save(product);
                }).flatMap(r -> Mono.empty())
                .onErrorResume(e -> Mono.error(() -> new DomainBusinessException(e.getMessage())));
    }

    public Mono<Void> addQuantity(final String code, final Integer quantity) {
        return repository.findByCode(code)
                .flatMap(product -> {
                    product.addQuantity(quantity);
                    return repository.save(product);
                }).flatMap(r -> Mono.empty());
    }

    public Mono<Product> create(final ProductCreateRequest request) {
        return Mono.just(request)
                .map(r -> requestMapper.toEntity(r))
                .log()
                .flatMap(product -> {
                    product.setQuantity(0);
                    return repository.save(product);
                });
    }
}

package com.github.fabriciolfj.inventario.domain.service;

import com.github.fabriciolfj.inventario.api.dto.request.ItemRequest;
import com.github.fabriciolfj.inventario.api.dto.request.OrderRequest;
import com.github.fabriciolfj.inventario.api.dto.request.ProductCreateRequest;
import com.github.fabriciolfj.inventario.api.exceptions.DomainBusinessException;
import com.github.fabriciolfj.inventario.api.exceptions.NotFoundException;
import com.github.fabriciolfj.inventario.api.mapper.ProductRequestMapper;
import com.github.fabriciolfj.inventario.domain.entity.Product;
import com.github.fabriciolfj.inventario.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final ProductRepository repository;
    private final ProductRequestMapper requestMapper;

    public Flux<Product> findAll() {
        return repository.findAll();
    }

    public Mono<?> exitQuantity(final String code, final Integer quantity, final LocalDate dateMov) {
        return repository.findByCode(code)
                .flatMap(product -> {
                    product.removeQuantity(quantity);
                    product.setMov(dateMov);
                    return repository.save(product);
                }).flatMap(r -> Mono.just("Product save: " + r))
                .onErrorMap(e -> new DomainBusinessException("Fail to remove quantity product " + code + " . Details: " + e.getMessage()));
    }

    public Mono<?> addQuantity(final String code, final Integer quantity) {
        return repository.findByCode(code)
                .flatMap(product -> {
                    product.addQuantity(quantity);
                    return repository.save(product);
                }).flatMap(r -> Mono.just("Product save: " + r))
                .onErrorMap(e -> new NotFoundException("Product not found, code: " + code + " .Details: " + e.getMessage()));
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

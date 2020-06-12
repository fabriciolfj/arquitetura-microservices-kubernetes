package com.github.fabriciolfj.product.domain.repository;

import com.github.fabriciolfj.product.domain.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Mono<Product> findByCode(final String code);
}

package com.github.fabriciolfj.inventario.domain.repository;

import com.github.fabriciolfj.inventario.domain.entity.Product;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {

    @Query("Select * from Product where code = :code")
    Mono<Product> findByCode(final String code);
}

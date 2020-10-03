package com.github.fabriciolfj.inventario.api.controller;

import com.github.fabriciolfj.inventario.api.dto.request.ProductCreateRequest;
import com.github.fabriciolfj.inventario.domain.entity.Product;
import com.github.fabriciolfj.inventario.domain.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("/inventario")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> create(@RequestBody final ProductCreateRequest request) {
        return service.create(request);
    }
    
    @PutMapping("/add/{code}/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> addQuantity(@PathVariable("code") final String code, @PathVariable("quantity") final Integer quantity) {
        return service.addQuantity(code, quantity);
    }

    @PutMapping("/remove/{code}/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<?> removeQuantity(@PathVariable("code") final String code, @PathVariable("quantity") final Integer quantity) {
        return service.exitQuantity(code, quantity);
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Flux<Product> findAll() {
        return service.findAll();
    }

}

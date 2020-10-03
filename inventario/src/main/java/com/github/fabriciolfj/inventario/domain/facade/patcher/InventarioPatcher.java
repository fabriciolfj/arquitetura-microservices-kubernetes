package com.github.fabriciolfj.inventario.domain.facade.patcher;

import com.github.fabriciolfj.inventario.api.dto.request.OrderRequest;
import com.github.fabriciolfj.inventario.api.exceptions.DomainBusinessException;
import com.github.fabriciolfj.inventario.domain.entity.enuns.StatusOrder;
import com.github.fabriciolfj.inventario.domain.message.OrderProducer;
import com.github.fabriciolfj.inventario.domain.service.InventarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
@Slf4j
@RequiredArgsConstructor
public class InventarioPatcher {

    private final InventarioService inventarioService;
    private final OrderProducer orderProducer;

    @Transactional(propagation = Propagation.REQUIRED)
    public Mono<?> process(final OrderRequest request) {
        log.info("Iniciando processo do pedido: " + request);
        return execute(request)
                .doOnSuccess(r -> {
                    orderProducer.send(request, StatusOrder.EMITIDO);
                }).doOnError(e -> {
                    orderProducer.send(request, StatusOrder.SEM_ESTOQUE);
                }).log();
    }

    private Mono<Void> execute(OrderRequest request) {
        return Flux.fromIterable(request.getItems())
                .timeout(Duration.ofSeconds(2))
                .flatMap(value -> inventarioService.exitQuantity(value.getCode(), value.getQuantity(),  request.getDataEmissao()))
                .doOnError(t -> new DomainBusinessException(t.getMessage())).log().then();
    }
}

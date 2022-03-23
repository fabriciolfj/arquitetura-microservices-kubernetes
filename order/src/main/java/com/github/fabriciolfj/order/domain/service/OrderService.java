package com.github.fabriciolfj.order.domain.service;

import com.github.fabriciolfj.order.api.dto.request.OrderRequest;
import com.github.fabriciolfj.order.api.dto.response.OrderResponse;
import com.github.fabriciolfj.order.api.exceptions.OrderNotFoundException;
import com.github.fabriciolfj.order.api.exceptions.OrderProcessException;
import com.github.fabriciolfj.order.api.mapper.OrderResponseMapper;
import com.github.fabriciolfj.order.domain.entity.enuns.Status;
import com.github.fabriciolfj.order.domain.facade.create.OrderCreate;
import com.github.fabriciolfj.order.domain.facade.fetcher.CustomerFetcher;
import com.github.fabriciolfj.order.domain.integracao.message.EstoqueIntegration;
import com.github.fabriciolfj.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Optional.of;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final OrderCreate createOrder;
    private final OrderResponseMapper mapper;
    private final CustomerFetcher customerFetcher;
    private final EstoqueIntegration estoqueIntegration;

    public void update(final String codeOrder) {
        repository.findByCode(codeOrder)
                .map(order -> {
                    order.setStatus(Status.ENTREGUE);
                    return repository.save(order);
                }).orElseThrow(() -> new OrderNotFoundException(codeOrder));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public OrderResponse create(final OrderRequest request) {
        customerFetcher.existsCustomer(request.getCustomer());
        return of(createOrder.create(request))
                .map(repository::save)
                .map(order -> {
                    var response = mapper.toResponse(order);
                    estoqueIntegration.send(response);
                    return response;
                })
                .orElseThrow(() ->  new OrderProcessException());
    }

    public OrderResponse findByCode(final String code) {
        return repository.findByCode(code)
                .map(order -> mapper.toResponse(order))
                .orElseThrow(() -> new OrderNotFoundException(code));
    }
}

package com.github.fabriciolfj.order.domain.repository;

import com.github.fabriciolfj.order.api.dto.response.OrderResponse;
import com.github.fabriciolfj.order.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByCode(final String code);
}

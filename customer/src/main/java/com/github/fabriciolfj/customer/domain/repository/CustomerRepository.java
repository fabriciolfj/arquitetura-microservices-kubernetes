package com.github.fabriciolfj.customer.domain.repository;

import com.github.fabriciolfj.customer.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCode(final String code);
}

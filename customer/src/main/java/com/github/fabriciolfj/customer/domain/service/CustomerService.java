package com.github.fabriciolfj.customer.domain.service;

import com.github.fabriciolfj.customer.api.dto.CustomerDTO;
import com.github.fabriciolfj.customer.api.exceptions.CustomerCreateException;
import com.github.fabriciolfj.customer.api.exceptions.CustomerNotFoundException;
import com.github.fabriciolfj.customer.api.exceptions.CustomerUpdateException;
import com.github.fabriciolfj.customer.api.mapper.CustomerMapper;
import com.github.fabriciolfj.customer.domain.entity.Customer;
import com.github.fabriciolfj.customer.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.of;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public Customer create(final CustomerDTO dto) {
        return of(mapper.toEntity(dto))
                .map(customerRepository::save)
                .orElseThrow(() -> new CustomerCreateException(dto));
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findByCode(final String code) {
        return customerRepository.findByCode(code)
                .orElseThrow(() -> new CustomerNotFoundException(code));
    }

    public void delete(final String code) {
        try {
            final var customer = findByCode(code);
            customerRepository.delete(customer);
        } catch (Exception e) { };
    }

    public Customer update(final CustomerDTO dto, final String code) {
        return of(findByCode(code)).map(c -> {
            c.setName(dto.getName());
            customerRepository.save(c);
            return c;
        }).orElseThrow(() -> new CustomerUpdateException(code));
    }
}

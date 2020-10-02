package com.github.fabriciolfj.customer.api.controller;

import com.github.fabriciolfj.customer.api.dto.CustomerDTO;
import com.github.fabriciolfj.customer.domain.entity.Customer;
import com.github.fabriciolfj.customer.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{code}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Customer findByCode(@PathVariable("code") final String code) {
        return customerService.findByCode(code);
    }

    @PutMapping("/{code}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Customer update(@RequestBody final CustomerDTO dto, @PathVariable("code") final String code) {
        return customerService.update(dto, code);
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final String code) {
        customerService.delete(code);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@RequestBody final CustomerDTO dto) {
        return customerService.create(dto);
    }
}

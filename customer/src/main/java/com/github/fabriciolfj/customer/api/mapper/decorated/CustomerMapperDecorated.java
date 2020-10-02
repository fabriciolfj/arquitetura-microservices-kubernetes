package com.github.fabriciolfj.customer.api.mapper.decorated;

import com.github.fabriciolfj.customer.api.dto.CustomerDTO;
import com.github.fabriciolfj.customer.api.mapper.CustomerMapper;
import com.github.fabriciolfj.customer.domain.entity.Customer;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@NoArgsConstructor
public abstract class CustomerMapperDecorated implements CustomerMapper {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Customer toEntity(CustomerDTO dto) {
        final Customer customer = customerMapper.toEntity(dto);
        customer.setCode(UUID.randomUUID().toString());

        return customer;
    }
}

package com.github.fabriciolfj.inventario.api.mapper.decorated;

import com.github.fabriciolfj.inventario.api.dto.request.OrderRequest;
import com.github.fabriciolfj.inventario.api.dto.request.OrderStatusRequest;
import com.github.fabriciolfj.inventario.api.mapper.OrderStatusMapper;
import com.github.fabriciolfj.inventario.domain.entity.enuns.StatusOrder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class OrderStatusMapperDecorated implements OrderStatusMapper {

    @Override
    public OrderStatusRequest toRequest(OrderRequest orderRequest, StatusOrder status) {
        return OrderStatusRequest
                .builder()
                .code(orderRequest.getCode())
                .status(status.getDescricao())
                .build();
    }
}

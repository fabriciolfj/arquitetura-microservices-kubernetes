package com.github.fabriciolfj.inventario.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemRequest {

    private String code;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal total;
}

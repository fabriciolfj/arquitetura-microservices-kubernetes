package com.github.fabriciolfj.order.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemResponse {

    private String code;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal total;
}

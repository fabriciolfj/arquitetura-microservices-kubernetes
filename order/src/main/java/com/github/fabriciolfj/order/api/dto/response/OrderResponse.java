package com.github.fabriciolfj.order.api.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.fabriciolfj.order.domain.core.CustomLocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private String descricao;
    private BigDecimal total;
    private String code;
    private String customer;
    private List<ItemResponse> items;
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDate dataEmissao;
}

package com.github.fabriciolfj.inventario.api.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.fabriciolfj.inventario.domain.core.CustomLocalDateDeserializer;
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
public class OrderRequest {

    private String descricao;
    private BigDecimal total;
    private String code;
    private String customer;
    private List<ItemRequest> items;
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    private LocalDate dataEmissao;
}

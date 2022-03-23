package com.github.fabriciolfj.order.api.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ItemRequest {

    @NotBlank(message = "Informe o código do produto")
    private String code;
    @NotBlank(message = "Informe a quantidade do produto.")
    private Integer quantity;
}

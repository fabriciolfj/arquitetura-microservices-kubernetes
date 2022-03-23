package com.github.fabriciolfj.product.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponseDTO {

    @NotBlank
    private String name;
    @NotNull
    private BigDecimal price;
    @NotBlank
    private String code;
}

package com.github.fabriciolfj.inventario.domain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.fabriciolfj.inventario.api.exceptions.DomainBusinessException;
import com.github.fabriciolfj.inventario.infra.util.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table("product")
public class Product {

    @Id
    private Long id;
    private String code;
    private Integer quantity;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate mov;

    public void addQuantity(final Integer quantity) {
        this.quantity += quantity;
    }

    public void removeQuantity(final Integer quantity) {
        this.quantity -= quantity;

        if(this.quantity < 0) {
            throw new DomainBusinessException("Produto sem estoque, id: " + id);
        }
    }
}

package com.github.fabriciolfj.inventario.domain.entity;

import com.github.fabriciolfj.inventario.api.exceptions.DomainBusinessException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table("product")
public class Product {

    @Id
    private Long id;
    private String code;
    private Integer quantity;

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

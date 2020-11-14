package com.fabriciolfj.github.entrega.domain.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Item extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "itemSequence", sequenceName = "itemSequenceid_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemSequence")
    @EqualsAndHashCode.Include
    private Long id;
    private String code;
    private BigDecimal valor;
    private Integer quantidade;
    @ManyToOne
    @JoinColumn(name = "entrega_id")
    private Entrega entrega;

}

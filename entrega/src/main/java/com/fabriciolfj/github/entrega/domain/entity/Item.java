package com.fabriciolfj.github.entrega.domain.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item")
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "itemSequence", sequenceName = "itemSequenceid_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemSequence")
    @EqualsAndHashCode.Include
    public Long id;
    public String code;
    public BigDecimal valor;
    public Integer quantidade;
    @ManyToOne
    @JoinColumn(name = "entrega_id")
    @JsonbTransient
    public Entrega entrega;

}

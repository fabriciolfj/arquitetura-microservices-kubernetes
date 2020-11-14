package com.fabriciolfj.github.entrega.domain.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "entrega")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Entrega extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "entregaSequence", sequenceName = "entregaId_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entregaSequence")
    @EqualsAndHashCode.Include
    private Long id;
    private String cliente;
    private String documento;
    private String destino;
    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
    private List<Item> itens;
}

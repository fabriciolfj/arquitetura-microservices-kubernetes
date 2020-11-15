package com.fabriciolfj.github.entrega.domain.entity;

import com.fabriciolfj.github.entrega.domain.entity.enuns.StatusEntrega;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@ToString
@Entity
@Table(name = "entrega")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Entrega extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "entregaSequence", sequenceName = "entregaId_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entregaSequence")
    @EqualsAndHashCode.Include
    public Long id;
    public String cliente;
    public String documento;
    public String destino;
    @Enumerated(EnumType.STRING)
    public StatusEntrega statusEntrega;
    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
    public List<Item> itens;
}

package com.fabriciolfj.github.entrega.domain.entity;

import com.fabriciolfj.github.entrega.domain.entity.enuns.StatusEntrega;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "entrega")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(
        name = "Entrega.cliente",
        query = "SELECT e FROM Entrega e join fetch e.itens i WHERE e.documento = :pardoc AND e.statusEntrega = :parStatus"
)
public class Entrega extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "entregaSequence", sequenceName = "entregaId_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entregaSequence")
    @EqualsAndHashCode.Include
    @ToString.Include
    public Long id;
    @ToString.Include
    public String cliente;
    @ToString.Include
    public String documento;
    @ToString.Include
    public String destino;
    @Enumerated(EnumType.STRING)
    public StatusEntrega statusEntrega;
    @Column(name = "code_order")
    public String order;
    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Item> itens;
}

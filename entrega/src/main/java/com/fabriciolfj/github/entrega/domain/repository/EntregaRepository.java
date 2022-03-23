package com.fabriciolfj.github.entrega.domain.repository;

import com.fabriciolfj.github.entrega.domain.entity.Entrega;
import com.fabriciolfj.github.entrega.domain.entity.enuns.StatusEntrega;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class EntregaRepository {

    @Inject
    private EntityManager entityManager;

    public List<Entrega> findEntregasRecebidas(final String documento) {
        var entregas = entityManager.createNamedQuery("Entrega.cliente").setParameter("pardoc", documento).setParameter("parStatus", StatusEntrega.RECEBIDA)
                .getResultList();

        return entregas;
    }
}

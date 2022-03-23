package com.fabriciolfj.github.entrega.domain.facade.validation;

import com.fabriciolfj.github.entrega.api.exceptions.ItensEntregaException;
import com.fabriciolfj.github.entrega.api.exceptions.QuantidadeDeEntregasException;
import com.fabriciolfj.github.entrega.domain.entity.Entrega;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class SaidaEntregaValidation {

    public Entrega validarSaida(final List<Entrega> entregas, final Entrega entrega) {
        return validarQuantidadeEntrega(entregas, entrega);
    }

    private Entrega validarQuantidadeEntrega(final List<Entrega> entregas, final Entrega entrega) {
        if (entregas.size() > 1 || entregas.isEmpty()) {
            throw new QuantidadeDeEntregasException("Clientes possui mais de uma entrega pendente ou sem entregas pendentes, por favor efetue a baixa");
        }

        return validarItens(entregas.get(0), entrega);
    }

    private Entrega validarItens(final Entrega entregaSalva, final Entrega entrega) {
        var quantidade = entregaSalva.itens.stream().map(item ->
                entrega.itens.stream().filter(saida -> saida.code.equalsIgnoreCase(item.code) && saida.quantidade.compareTo(item.quantidade) == 0)
        .count()).findFirst();

        if (quantidade.isPresent() && quantidade.get().intValue() == entrega.itens.size()) {
            return entregaSalva;
        }

        throw new ItensEntregaException("Divergencia entre a entrega salva com a solicitada");
    }
}

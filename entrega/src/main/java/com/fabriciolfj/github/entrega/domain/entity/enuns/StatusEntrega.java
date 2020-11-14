package com.fabriciolfj.github.entrega.domain.entity.enuns;

import com.fabriciolfj.github.entrega.api.exceptions.StatusEntregaNotFoundException;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum StatusEntrega {

    RECEBIDA("recebida"), SAIDA_PARA_ENTREGA("saidaParaEntrega"), ENTREGUE("entregue");

    private String descricao;

    StatusEntrega(final String descricao) {
       this.descricao = descricao;
    }

    public static StatusEntrega toEnum(final String descricao) {
        return Stream.of(StatusEntrega.values())
                .filter(d -> d.equals(descricao))
                .findFirst()
                .orElseThrow(() -> new StatusEntregaNotFoundException("Status entrega não localizado: " + descricao));
    }
}

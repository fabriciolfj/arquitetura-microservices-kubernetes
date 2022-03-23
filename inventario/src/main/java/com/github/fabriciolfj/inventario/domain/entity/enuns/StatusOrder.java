package com.github.fabriciolfj.inventario.domain.entity.enuns;

import com.github.fabriciolfj.inventario.api.exceptions.StatusNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum StatusOrder {
    CRIADO("criado"), EMITIDO("emitido"), SEM_ESTOQUE("noestoque");

    private String descricao;

    private static StatusOrder toEnum(final String descricao) {
        return Stream.of(StatusOrder.values())
                .filter(s -> s.getDescricao().equalsIgnoreCase(descricao))
                .findFirst()
                .orElseThrow(() -> new StatusNotFoundException(descricao));
    }


}

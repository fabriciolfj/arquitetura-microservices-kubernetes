package com.github.fabriciolfj.order.domain.entity.enuns;

import com.github.fabriciolfj.order.api.exceptions.StatusNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Status {
    CRIADO("criado"), EMITIDO("emitido"), SEM_ESTOQUE("noestoque"), ENTREGUE("entrege");

    private String descricao;

    private static Status toEnum(final String descricao) {
        return Stream.of(Status.values())
                .filter(s -> s.getDescricao().equalsIgnoreCase(descricao))
                .findFirst()
                .orElseThrow(() -> new StatusNotFoundException(descricao));
    }


}

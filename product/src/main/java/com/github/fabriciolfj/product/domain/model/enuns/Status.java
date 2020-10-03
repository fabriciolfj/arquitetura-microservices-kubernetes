package com.github.fabriciolfj.product.domain.model.enuns;

import com.github.fabriciolfj.product.api.exceptions.StatusNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum  Status {

    INATIVO("inativo"), ATIVO("ativo");

    private String description;

    public static Status toEnum(final String value) {
        return Stream.of(Status.values())
                .filter(s -> s.description.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new StatusNotFoundException("Status not found: " + value));
    }
}

package com.github.fabriciolfj.inventario.domain.core;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateDeserializer extends StdDeserializer<LocalDate> {
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    protected CustomLocalDateDeserializer(JavaType valueType) {
        super(valueType);
    }

    @Override
    public LocalDate deserialize(JsonParser json, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        var date = json.getText();
        return LocalDate.parse(date, dtf);
    }
}

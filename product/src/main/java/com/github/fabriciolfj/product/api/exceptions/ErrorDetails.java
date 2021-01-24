package com.github.fabriciolfj.product.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@Setter
public class ErrorDetails {
    private long timestamp;
    private String message;
    private String details;


}
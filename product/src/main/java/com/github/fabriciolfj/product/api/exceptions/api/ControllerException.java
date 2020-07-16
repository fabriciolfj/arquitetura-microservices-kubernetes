package com.github.fabriciolfj.product.api.exceptions.api;

import com.github.fabriciolfj.product.api.exceptions.ProductCreateException;
import com.github.fabriciolfj.product.api.exceptions.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerException {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(final ProductNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(ProductCreateException.class)
    public ResponseEntity<String> handleProductCreateException(final ProductCreateException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}

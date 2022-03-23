package com.github.fabriciolfj.product.api.exceptions.api;

import com.github.fabriciolfj.product.api.exceptions.ErrorDetails;
import com.github.fabriciolfj.product.api.exceptions.ProductCreateException;
import com.github.fabriciolfj.product.api.exceptions.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.util.Objects;

@Slf4j
@ControllerAdvice
@Configuration
public class ControllerException implements ErrorWebExceptionHandler {

    public static final String UTF_8 = "UTF-8";

    @Override
    public Mono<Void> handle(final ServerWebExchange serverWebExchange, final Throwable throwable) {
        log.error(throwable.getMessage(), throwable);

        var message = Objects.nonNull(throwable.getLocalizedMessage()) ? throwable.getLocalizedMessage() : "";
        var errorDetails = new ErrorDetails(System.currentTimeMillis(),
                message,
                throwable.getMessage());

        var bufferFactory = serverWebExchange.getResponse().bufferFactory();
        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        httpStatus = productNotFound(throwable, errorDetails, httpStatus);
        serverWebExchange.getResponse().setStatusCode(httpStatus);
        serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        String errorDetailsMessage = errorDetails.getMessage();

        if (Objects.isNull(errorDetailsMessage)) {
            errorDetailsMessage = "";
        }

        var dataBuffer = bufferFactory.wrap(errorDetailsMessage.getBytes(Charset.forName(UTF_8)));
        return serverWebExchange.getResponse().writeWith(Mono.just(dataBuffer));
    }

    private HttpStatus productNotFound(Throwable throwable, ErrorDetails errorDetails, HttpStatus httpStatus) {
        if (throwable instanceof ProductNotFoundException) {
            errorDetails.setMessage(throwable.getMessage());
            httpStatus = HttpStatus.NOT_FOUND;
            return httpStatus;
        }

        return productCreateException(throwable, errorDetails, httpStatus);
    }

    private HttpStatus productCreateException(Throwable throwable, ErrorDetails errorDetails, HttpStatus httpStatus) {
        if (throwable instanceof ProductCreateException) {
            errorDetails.setMessage(throwable.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
            return httpStatus;
        }

        return productUpdateException(throwable, errorDetails, httpStatus);
    }

    private HttpStatus productUpdateException(Throwable throwable, ErrorDetails errorDetails, HttpStatus httpStatus) {
        if (throwable instanceof ProductNotFoundException) {
            errorDetails.setMessage(throwable.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
            return httpStatus;
        }

        return httpStatus;
    }


}

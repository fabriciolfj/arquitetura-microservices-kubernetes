package com.github.fabriciolfj.order.controller;

import br.com.six2six.fixturefactory.Fixture;
import com.github.fabriciolfj.order.OrderApplicationTests;
import com.github.fabriciolfj.order.api.dto.request.OrderRequest;
import com.github.fabriciolfj.order.api.dto.response.ProductResponse;
import com.github.fabriciolfj.order.domain.facade.fetcher.CustomerFetcher;
import com.github.fabriciolfj.order.domain.integracao.http.ProductClient;
import com.github.fabriciolfj.order.template.OrderRequestTemplate;
import com.github.fabriciolfj.order.template.ProductResponseTemplate;
import io.restassured.http.ContentType;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static io.restassured.RestAssured.given;

@DisplayName("Teste do controller orders")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderControllerTest extends OrderApplicationTests {

    @MockBean
    private CustomerFetcher customerFetcher;

    @MockBean
    private ProductClient productClient;

    @Test
    void criar_order_com_sucesso() {
        final OrderRequest order = Fixture.from(OrderRequest.class).gimme(OrderRequestTemplate.ORDER_REQUEST_VALID);
        final ProductResponse product = Fixture.from(ProductResponse.class).gimme(ProductResponseTemplate.PRODUCT_RESPONSE_VALID);

        Mockito.doNothing().when(customerFetcher).existsCustomer(Mockito.anyString());
        Mockito.when(productClient.getProduct(Mockito.anyString())).thenReturn(product);

        given()
                .contentType(ContentType.JSON)
                .body(order)
                .post("/orders")
                .then()
                .log().all(true)
                .statusCode(HttpStatus.SC_CREATED);

    }
}

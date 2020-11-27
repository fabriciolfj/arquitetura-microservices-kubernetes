package com.github.fabriciolfj.order.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.github.fabriciolfj.order.api.dto.response.ProductResponse;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductResponseTemplate implements TemplateLoader {

    public static final String PRODUCT_RESPONSE_VALID = "productResponseValid";

    @Override
    public void load() {
        Fixture.of(ProductResponse.class).addTemplate(PRODUCT_RESPONSE_VALID, new Rule(){
            {
                add("code", UUID.randomUUID().toString());
                add("price", BigDecimal.valueOf(10.00));
            }
        });

    }
}

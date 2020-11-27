package com.github.fabriciolfj.order.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.github.fabriciolfj.order.api.dto.request.ItemRequest;
import com.github.fabriciolfj.order.api.dto.request.OrderRequest;

import java.util.UUID;

public class OrderRequestTemplate implements TemplateLoader {

    public static final String ORDER_REQUEST_VALID = "orderRequestValid";

    @Override
    public void load() {
        Fixture.of(OrderRequest.class).addTemplate(ORDER_REQUEST_VALID, new Rule(){
            {
                add("descricao", "teste");
                add("customer", UUID.randomUUID().toString());
                add("items", has(1).of(ItemRequest.class, ItemRequestTemplate.ITEM_REQUEST_VALID));
            }
        });
    }
}

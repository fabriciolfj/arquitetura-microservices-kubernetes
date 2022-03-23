package com.github.fabriciolfj.order.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.github.fabriciolfj.order.api.dto.request.ItemRequest;

import java.util.UUID;

public class ItemRequestTemplate implements TemplateLoader {

    public static final String ITEM_REQUEST_VALID = "itemRequestValid";

    @Override
    public void load() {
        Fixture.of(ItemRequest.class).addTemplate(ITEM_REQUEST_VALID, new Rule(){
            {
                add("code", UUID.randomUUID().toString());
                add("quantity", 10);
            }
        });
    }
}

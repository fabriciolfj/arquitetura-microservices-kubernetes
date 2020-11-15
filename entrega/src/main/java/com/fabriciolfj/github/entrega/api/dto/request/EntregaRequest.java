package com.fabriciolfj.github.entrega.api.dto.request;

import java.util.List;

public class EntregaRequest {

    public String documento;
    public String cliente;
    public String destino;
    public List<ItemRequest> itens;
}

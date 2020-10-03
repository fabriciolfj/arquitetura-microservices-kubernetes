package com.github.fabriciolfj.order.api.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class OrderRequest {

    @NotBlank(message = "Informe a descrição do pedido.")
    private String descricao;
    @Size(min = 1, message = "Informe ao menos 1 item")
    private List<ItemRequest> items;
    @NotBlank(message = "Informe o codigo do cliente")
    private String customer;
}

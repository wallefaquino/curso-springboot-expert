package com.walleftech.csbe.entities.dto;

import com.walleftech.csbe.entities.ItemPedido;
import com.walleftech.csbe.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    @NotNull(message = "Código do cliente é obrigatório!")
    private Long cliente;

    @NotNull(message = "Campo total é obrigatório!")
    private BigDecimal total;

    @NotEmptyList(message = "A lista de itens não pode ser ser vazia!")
    private List<ItemPedidoDTO> itens;
}

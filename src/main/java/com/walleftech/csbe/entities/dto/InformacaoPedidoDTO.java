package com.walleftech.csbe.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacaoPedidoDTO {

    private Long codigo;
    private String nomeCliente;
    private BigDecimal total;
    private String dataPedido;
    private List<InformacaoItemPedidoDTO> itens;
}

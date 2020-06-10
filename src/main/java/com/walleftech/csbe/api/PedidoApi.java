package com.walleftech.csbe.api;

import com.walleftech.csbe.entities.ItemPedido;
import com.walleftech.csbe.entities.Pedido;
import com.walleftech.csbe.entities.dto.InformacaoItemPedidoDTO;
import com.walleftech.csbe.entities.dto.InformacaoPedidoDTO;
import com.walleftech.csbe.entities.dto.PedidoDTO;
import com.walleftech.csbe.exceptions.RecursoNaoEncontradoException;
import com.walleftech.csbe.services.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoApi {

    private PedidoService service;

    public PedidoApi(PedidoService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<Pedido> salvar(@Valid @RequestBody PedidoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
    }

    @GetMapping("/{id}")
    public InformacaoPedidoDTO buscarPorId(@PathVariable Long id) {
        return service
                .buscarPorId(id)
                .map(p -> converter(p))
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado com esse id!"));
    }

    private InformacaoPedidoDTO converter(Pedido pedido) {
        return InformacaoPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .total(pedido.getTotal())
                .nomeCliente(pedido.getCliente().getNome())
                .itens(converter(pedido.getItensPedido()))
                .build();
    }

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens) {
        if(CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }

        return itens.stream().map(
                item -> InformacaoItemPedidoDTO
                            .builder()
                            .descricaoProduto(item.getProduto().getDescricao())
                            .precoUnitario(item.getProduto().getPreco())
                            .quantidade(item.getQuantidade())
                            .build()
        ).collect(Collectors.toList());
    }
}

package com.walleftech.csbe.services.impl;

import com.walleftech.csbe.entities.Cliente;
import com.walleftech.csbe.entities.ItemPedido;
import com.walleftech.csbe.entities.Pedido;
import com.walleftech.csbe.entities.Produto;
import com.walleftech.csbe.entities.dto.InformacaoItemPedidoDTO;
import com.walleftech.csbe.entities.dto.InformacaoPedidoDTO;
import com.walleftech.csbe.entities.dto.ItemPedidoDTO;
import com.walleftech.csbe.entities.dto.PedidoDTO;
import com.walleftech.csbe.exceptions.RecursoNaoEncontradoException;
import com.walleftech.csbe.exceptions.RegraNegocioException;
import com.walleftech.csbe.repositories.PedidoRepository;
import com.walleftech.csbe.services.ClienteService;
import com.walleftech.csbe.services.ItemPedidoService;
import com.walleftech.csbe.services.PedidoService;
import com.walleftech.csbe.services.ProdutoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository repository;
    private ProdutoService produtoService;
    private ClienteService clienteService;
    private ItemPedidoService itemPedidoService;

    public PedidoServiceImpl(
            PedidoRepository repository,
            ProdutoService produtoService,
            ClienteService clienteService,
            ItemPedidoService itemPedidoService
    ){
        this.repository = repository;
        this.produtoService = produtoService;
        this.clienteService = clienteService;
        this.itemPedidoService = itemPedidoService;
    }

    @Override
    public Optional<Pedido> buscarPorId(Long id) {
        return repository.findByIdFetchItens(id);
    }

    @Override
    public Pedido salvar(PedidoDTO dto) {
        Cliente cliente = clienteService.buscarPorId(dto.getCliente());

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente((cliente));

        List<ItemPedido> itensPedidos = converterItens(pedido, dto.getItens());
        repository.save(pedido);
        itemPedidoService.salvarLista(itensPedidos);
        pedido.setItensPedido(itensPedidos);
        return pedido;
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens) {
        if(itens.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar pedido sem itens");
        }

        return itens
                .stream()
                .map( dto -> {
                    Produto produto = produtoService.buscarPorId(dto.getProduto());

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}

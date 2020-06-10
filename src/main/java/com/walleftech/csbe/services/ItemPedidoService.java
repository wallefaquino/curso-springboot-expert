package com.walleftech.csbe.services;

import com.walleftech.csbe.entities.ItemPedido;

import java.util.List;

public interface ItemPedidoService {

    List<ItemPedido> listar();

    ItemPedido buscarPorId(Long id);

    ItemPedido salvar(com.walleftech.csbe.entities.ItemPedido itemPedido);

    ItemPedido atualizar(Long id, ItemPedido itemPedido);

    void remover(Long id);

    void salvarLista(List<ItemPedido> itens);
}

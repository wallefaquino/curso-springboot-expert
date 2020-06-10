package com.walleftech.csbe.services.impl;

import com.walleftech.csbe.entities.ItemPedido;
import com.walleftech.csbe.exceptions.RecursoNaoEncontradoException;
import com.walleftech.csbe.repositories.ItemPedidoRepository;
import com.walleftech.csbe.services.ItemPedidoService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoServiceImpl implements ItemPedidoService {

    private ItemPedidoRepository repository;

    public ItemPedidoServiceImpl(ItemPedidoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ItemPedido> listar() {
        return listar();
    }

    @Override
    public ItemPedido buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Item do pedido não encontrado com esse id: " + id));
    }

    @Override
    public ItemPedido salvar(ItemPedido itemPedido) {
        return repository.save(itemPedido);
    }

    @Override
    public ItemPedido atualizar(Long id, ItemPedido itemPedido) {

        return repository
                .findById(id)
                .map(itemPedidoAtual ->{
                    itemPedido.setId(itemPedidoAtual.getId());
                    return repository.save(itemPedido);
                }).orElseThrow(() ->new EmptyResultDataAccessException(1));
    }

    @Override
    public void remover(Long id) {
        ItemPedido itemPedido = buscarPorId(id);

        repository.deleteById(itemPedido.getId());
    }

    @Override
    public void salvarLista(List<ItemPedido> itens) {
        repository.saveAll(itens);
    }


}

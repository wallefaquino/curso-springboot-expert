package com.walleftech.csbe.services;

import com.walleftech.csbe.entities.Produto;

import java.util.List;

public interface ProdutoService {

    List<Produto> listar();

    Produto buscarPorId(Long id);

    Produto salvar(Produto Produto);

    Produto atualizar(Long id, Produto Produto);

    void remover(Long id);
}

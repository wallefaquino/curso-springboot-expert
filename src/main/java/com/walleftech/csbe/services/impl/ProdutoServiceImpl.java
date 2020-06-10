package com.walleftech.csbe.services.impl;

import com.walleftech.csbe.entities.Produto;
import com.walleftech.csbe.exceptions.RecursoNaoEncontradoException;
import com.walleftech.csbe.repositories.ProdutoRepository;
import com.walleftech.csbe.services.ProdutoService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private ProdutoRepository repository;

    public ProdutoServiceImpl(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Produto> listar() {
        return repository.findAll();
    }

    @Override
    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow( () -> new RecursoNaoEncontradoException("Produto não encontrado com esse id."));
    }

    @Override
    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    @Override
    public Produto atualizar(Long id, Produto produto) {

        return repository
                .findById(id)
                .map(produtoAtual -> {
                    produto.setId(produtoAtual.getId());
                    return repository.save(produto);
                }).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    @Override
    public void remover(Long id) {
        Produto produto = buscarPorId(id);

        repository.deleteById(id);
    }
}

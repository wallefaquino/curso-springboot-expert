package com.walleftech.csbe.services.impl;

import com.walleftech.csbe.entities.Cliente;
import com.walleftech.csbe.exceptions.RecursoNaoEncontradoException;
import com.walleftech.csbe.repositories.ClienteRepository;
import com.walleftech.csbe.services.ClienteService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository repository;

    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Cliente> listar() {
        return repository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com esse id"));
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public Cliente atualizar(Long id, Cliente cliente) {

        return repository
                .findById(id)
                .map( clienteAtual -> {
                    cliente.setId(clienteAtual.getId());
                    return repository.save(cliente);
                }).orElseThrow(() ->new EmptyResultDataAccessException(1));
    }

    @Override
    public void remover(Long id) {
        repository.deleteById(buscarPorId(id).getId());
    }
}

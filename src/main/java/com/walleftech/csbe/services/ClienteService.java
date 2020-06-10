package com.walleftech.csbe.services;

import com.walleftech.csbe.entities.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> listar();

    Cliente buscarPorId(Long id);

    Cliente salvar(Cliente cliente);

    Cliente atualizar(Long id, Cliente cliente);

    void remover(Long id);
}

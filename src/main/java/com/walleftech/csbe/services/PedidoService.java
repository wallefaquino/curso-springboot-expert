package com.walleftech.csbe.services;

import com.walleftech.csbe.entities.Pedido;
import com.walleftech.csbe.entities.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Optional<Pedido> buscarPorId(Long id);

    Pedido salvar(PedidoDTO dto);
}

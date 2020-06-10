package com.walleftech.csbe.repositories;

import com.walleftech.csbe.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(" select p from Pedido p left join fetch p.itensPedido where p.id = :id ")
    Optional<Pedido> findByIdFetchItens(@Param("id") Long id);
}

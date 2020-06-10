package com.walleftech.csbe.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "cliente")
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Campo nome é obrigatório!")
    @Column(name = "nome")
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private Set<Pedido> pedidos;
}

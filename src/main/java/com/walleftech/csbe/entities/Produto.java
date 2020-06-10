package com.walleftech.csbe.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao")
    @NotEmpty(message = "Campo descrição é obrigatório!")
    private String descricao;

    @Column(name = "preco_unitario")
    @NotNull(message = "Campo preço é obrigatório!")
    private BigDecimal preco;
}

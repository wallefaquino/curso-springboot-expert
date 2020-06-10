package com.walleftech.csbe.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotEmpty(message = "O campo Login é obrigatório")
    private String login;

    @Column
    @NotEmpty(message = "O campo Senha é obrigatório")
    private String senha;

    @Column
    private boolean admin;
}

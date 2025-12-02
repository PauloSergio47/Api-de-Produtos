package com.paulo.Api.de.produtos.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nomeDeUsuario;

    private String senha;

    private String role = "ROLE_USER";
}

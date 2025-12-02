package com.paulo.Api.de.produtos.repository;

import com.paulo.Api.de.produtos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNomeDeUsuario(String nomeDeUsuario);
}

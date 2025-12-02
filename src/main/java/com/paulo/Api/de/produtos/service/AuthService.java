package com.paulo.Api.de.produtos.service;

import com.paulo.Api.de.produtos.dto.AuthRequestDTO;
import com.paulo.Api.de.produtos.dto.AuthResponseDTO;
import com.paulo.Api.de.produtos.dto.RegisterRequestDTO;
import com.paulo.Api.de.produtos.model.Usuario;
import com.paulo.Api.de.produtos.repository.UsuarioRepository;
import com.paulo.Api.de.produtos.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthService( UsuarioRepository usuarioRepo,
                        PasswordEncoder passwordEncoder,
                        AuthenticationManager authManager,
                        JwtUtil jwtUtil) {
        this.usuarioRepo = usuarioRepo;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    public void registrar(RegisterRequestDTO dto) {
        if (usuarioRepo.findByNomeDeUsuario(dto.nomeDeUsuario()).isPresent()) {
            throw new RuntimeException("Username já está em uso.");
        }

        Usuario usuario = new Usuario();
        usuario.setNomeDeUsuario(dto.nomeDeUsuario());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));

        usuarioRepo.save(usuario);
    }

    public AuthResponseDTO login(AuthRequestDTO dto) {
        var authToken = new UsernamePasswordAuthenticationToken(
                dto.nomeDeUsuario(), dto.senha()
        );
        authManager.authenticate(authToken);

        String token = jwtUtil.gerarToken(dto.nomeDeUsuario());
        return new AuthResponseDTO(token);
    }
}


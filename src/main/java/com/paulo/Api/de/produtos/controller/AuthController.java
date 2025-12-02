package com.paulo.Api.de.produtos.controller;

import com.paulo.Api.de.produtos.dto.AuthRequestDTO;
import com.paulo.Api.de.produtos.dto.AuthResponseDTO;
import com.paulo.Api.de.produtos.dto.RegisterRequestDTO;
import com.paulo.Api.de.produtos.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registrar(@RequestBody @Valid RegisterRequestDTO dto) {
        authService.registrar(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthRequestDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
}


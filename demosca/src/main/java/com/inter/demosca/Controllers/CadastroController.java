package com.inter.demosca.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inter.demosca.Entities.UsuarioEntity;
import com.inter.demosca.Services.UsuarioService;

@RestController
public class CadastroController {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioService usuarioService;

    CadastroController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/cadastrar")
public ResponseEntity<?> cadastrar(@RequestBody UsuarioEntity dto) {
    // ...
    UsuarioEntity usuario = new UsuarioEntity();
    usuarioService.incluir(usuario);

    // Retorna um objeto JSON simples
    return ResponseEntity.ok(
        Map.of("message", "Usuário cadastrado com sucesso!")
    );
}

    @PostMapping("/api/login")
public ResponseEntity<?> login(@RequestBody UsuarioEntity dto) {

    boolean valido = usuarioService.validar(dto.getUsername(), dto.getPassword());

    if (!valido) {
        // CORREÇÃO: Retorna uma Map (JSON) com a mensagem de erro
        return ResponseEntity.status(401).body(
            Map.of("message", "Usuário ou senha inválidos")
        );
    }

    // O caminho de sucesso já retorna JSON
    return ResponseEntity.ok(
        Map.of(
            "token", "token_fake_123",
            "user", dto.getUsername()
        )
    );
}
}

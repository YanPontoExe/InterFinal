package com.inter.demosca.Controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.dao.DataIntegrityViolationException;

import com.inter.demosca.Entities.UsuarioEntity;
import com.inter.demosca.Services.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CadastroController {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody UsuarioEntity dto) {
        
        UsuarioEntity usuario = new UsuarioEntity();
        
        usuario.setUsername(dto.getUsername()); 

        String senhaCriptografada = passwordEncoder.encode(dto.getPassword());
        usuario.setPassword(senhaCriptografada);
        
        try {
            usuarioService.incluir(usuario);

            return ResponseEntity.ok(
                Map.of("message", "Usuário cadastrado com sucesso!")
            );
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(409).body(
                Map.of("message", "O nome de usuário já está em uso ou faltam dados obrigatórios.")
            );
        }
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody UsuarioEntity dto) {

        boolean valido = usuarioService.validar(dto.getUsername(), dto.getPassword());

        if (!valido) {
            return ResponseEntity.status(401).body(
                Map.of("message", "Usuário ou senha inválidos")
            );
        }

        return ResponseEntity.ok(
            Map.of(
                "token", "token_fake_123",
                "user", dto.getUsername()
            )
        );
    }
}
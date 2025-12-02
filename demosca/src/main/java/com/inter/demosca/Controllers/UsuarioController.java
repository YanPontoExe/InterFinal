package com.inter.demosca.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inter.demosca.Entities.UsuarioEntity;
import com.inter.demosca.Services.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/Usuario") //nomeando o path do Endpoint do controller, para ser executado no postman

public class UsuarioController {
    private final UsuarioService UsuarioService;
 
    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> listarTodos() {
        List<UsuarioEntity> lista = UsuarioService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }
 
    @PostMapping
    public ResponseEntity<UsuarioEntity> incluir(@RequestBody
    UsuarioEntity Usuario) {
        UsuarioEntity novo = UsuarioService.incluir(Usuario);
        if (novo != null) {
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
 
     @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> editar(@PathVariable int id,
    @RequestBody UsuarioEntity Usuario) {
        UsuarioEntity atualizado = UsuarioService.editar(id,Usuario);
        if (atualizado != null) {
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        UsuarioService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

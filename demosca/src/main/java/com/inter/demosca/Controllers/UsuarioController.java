package com.inter.demosca.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.inter.demosca.Repositories.UsuarioRepository;
import com.inter.demosca.Services.UsuarioService;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/Usuario") //nomeando o path do Endpoint do controller, para ser executado no postman

public class UsuarioController {
    private final UsuarioService UsuarioService;
 
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/cadastro")
    public String mostrarCadastro() {
        return "cadastroUsuario";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@RequestParam String username,
                            @RequestParam String password) {

        usuarioService.salvar(username, password);

        return "redirect:/";
    }
 
}

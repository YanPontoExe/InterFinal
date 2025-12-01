package com.inter.demosca.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inter.demosca.Services.UsuarioService;

@Controller
public class CadastroController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/cadastro")
    public String mostrarCadastro() {
        return "cadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@RequestParam String username,
                            @RequestParam String password) {

        usuarioService.salvar(username, password);

        return "redirect:/login";
    }
}

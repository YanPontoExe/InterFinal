package com.inter.demosca.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inter.demosca.Services.UsuarioService;

// @RestController
// public class CadastroController {

//     @Autowired
//     private UsuarioService usuarioService;

//     @GetMapping("/cadastro")
//     public String mostrarCadastro() {
//         return "cadastro";
//     }

//     @PostMapping("/cadastrar")
//     public String cadastrar(@RequestParam String username,
//                             @RequestParam String password) {

//         usuarioService.incluir(Usuario);

//         return "redirect:/login";
//     }
// }

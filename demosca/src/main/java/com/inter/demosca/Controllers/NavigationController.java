package com.inter.demosca.Controllers;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NavigationController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/access-fornecedor")
    public String cadastrar_fornecedor() {
        return "access-fornecedor";
    }

    @GetMapping("/access-marca")
    public String cadastrar_marca(){
        return "access-marca";
    }

    @GetMapping("/access-user")
    public String cadastrar_usuario(){
        return "access-user";
    }

    @GetMapping("/access-material")
    public String cadastrar_material(){
        return "access-material";
    }

    
    @PostMapping("/auth")
public String login(@RequestParam String username,
                    @RequestParam String password) {

    System.out.println("DEBUG LOGIN → username: " + username + ", password: " + password);

    UsernamePasswordAuthenticationToken authReq
        = new UsernamePasswordAuthenticationToken(username, password);
        System.out.println("Carregou usuario: " + username);
    return "redirect:/";    
}
}

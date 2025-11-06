package com.inter.demosca.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}

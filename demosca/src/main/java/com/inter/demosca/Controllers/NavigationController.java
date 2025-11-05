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
    public String fornecedor() {
        return "access-fornecedor";
    }

    @GetMapping("/access-marca")
    public String marca(){
        return "access-marca";
    }
}

package com.inter.demosca.Controllers;

import com.inter.demosca.Entities.MovimentacaoEstoqueView;
import com.inter.demosca.Services.MovimentacaoEstoqueViewService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoEstoqueViewController {

    private final MovimentacaoEstoqueViewService service;

    public MovimentacaoEstoqueViewController(MovimentacaoEstoqueViewService service) {
        this.service = service;
    }

    @GetMapping
    public List<MovimentacaoEstoqueView> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{material}") 
    public MovimentacaoEstoqueView buscarPorMaterial(@PathVariable String material) {
        return service.buscarPorMaterial(material);
    }
}

package com.inter.demosca.Controllers;

import java.util.List;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inter.demosca.Entities.EstoqueEntity;
import com.inter.demosca.Services.EstoqueService;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "api/Estoque") //nomeando o path do Endpoint do controller, para ser executado no postman

public class EstoqueController {
 private final EstoqueService EstoqueService;
 
    @GetMapping
    public ResponseEntity<List<EstoqueEntity>> listarTodos() {
        List<EstoqueEntity> lista = EstoqueService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }
 
    @PostMapping
    public ResponseEntity<EstoqueEntity> incluir(@RequestBody
    EstoqueEntity Estoque) {
        EstoqueEntity novo = EstoqueService.incluir(Estoque);
        if (novo != null) {
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
 
     @PutMapping("/{id}")
    public ResponseEntity<EstoqueEntity> editar(@PathVariable int id,
    @RequestBody EstoqueEntity Estoque) {
        EstoqueEntity atualizado = EstoqueService.editar(id,Estoque);
        if (atualizado != null) {
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        EstoqueService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

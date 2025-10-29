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

import com.inter.demosca.Entities.FornecedorEntity;
import com.inter.demosca.Services.FornecedorService;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/Fornecedor") //nomeando o path do Endpoint do controller, para ser executado no postman

public class FornecedorController {
    private final FornecedorService FornecedorService;
 
    @GetMapping
    public ResponseEntity<List<FornecedorEntity>> listarTodos() {
        List<FornecedorEntity> lista = FornecedorService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }
 
    @PostMapping
    public ResponseEntity<FornecedorEntity> incluir(@RequestBody
    FornecedorEntity Fornecedor) {
        FornecedorEntity novo = FornecedorService.incluir(Fornecedor);
        if (novo != null) {
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
 
     @PutMapping("/{id}")
    public ResponseEntity<FornecedorEntity> editar(@PathVariable int id,
    @RequestBody FornecedorEntity Fornecedor) {
        FornecedorEntity atualizado = FornecedorService.editar(id,Fornecedor);
        if (atualizado != null) {
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        FornecedorService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.inter.demosca.Controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inter.demosca.Entities.MaterialEntity;
import com.inter.demosca.Services.MaterialService;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/Material") //nomeando o path do Endpoint do controller, para ser executado no postman

public class MaterialController { 
    private final MaterialService MaterialService;

    //ativação das funções do material no SQL Server
    @GetMapping("/{id}/total-movimentacoes")  
    public ResponseEntity<Integer> getTotalMovimentacoes(@PathVariable Integer id) {
        Integer total = MaterialService.obterTotalMovimentacoes(id);
        return ResponseEntity.ok(total);
    }
    
    
    //Mapeamento dos endpoints do MaterialController
    @GetMapping
    public ResponseEntity<List<MaterialEntity>> listarTodos() {
        List<MaterialEntity> lista = MaterialService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }
    

 
    @PostMapping
    public ResponseEntity<MaterialEntity> incluir(@RequestBody
    MaterialEntity Material) {
        MaterialEntity novo = MaterialService.incluir(Material);
        if (novo != null) {
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
 
     @PutMapping("/{id}")
    public ResponseEntity<MaterialEntity> editar(@PathVariable int id,
    @RequestBody MaterialEntity Material) {
        MaterialEntity atualizado = MaterialService.editar(id,Material);
        if (atualizado != null) {
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        MaterialService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

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

import com.inter.demosca.Entities.EntradaEntity;
import com.inter.demosca.Services.EntradaService;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/Entrada") //nomeando o path do Endpoint do controller, para ser executado no postman

public class EntradaController { 
 private final EntradaService EntradaService;
 
    //Mapeia a function SQL de Listar todas as Entradas com um novo endpoint
    @GetMapping("/total-entradas")
    public List<EntradaEntity> listarTodas() {
        return EntradaService.listarTodas();
    }

    @GetMapping
    public ResponseEntity<List<EntradaEntity>> listarTodos() {
        List<EntradaEntity> lista = EntradaService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }
 
    @PostMapping
    public ResponseEntity<EntradaEntity> incluir(@RequestBody
    EntradaEntity Entrada) {
        EntradaEntity novo = EntradaService.incluir(Entrada);
        if (novo != null) {
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
 
     @PutMapping("/{id}")
    public ResponseEntity<EntradaEntity> editar(@PathVariable int id,
    @RequestBody EntradaEntity Entrada) {
        EntradaEntity atualizado = EntradaService.editar(id,Entrada);
        if (atualizado != null) {
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        EntradaService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

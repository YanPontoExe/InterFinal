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

import com.inter.demosca.Entities.MarcaEntity;
import com.inter.demosca.Services.MarcaService;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/api/Marca") //nomeando o path do Endpoint do controller, para ser executado no postman

public class MarcaController {
    private final MarcaService MarcaService;
 
    @GetMapping
    public ResponseEntity<List<MarcaEntity>> listarTodos() {
        List<MarcaEntity> lista = MarcaService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }
 
    @PostMapping
    public ResponseEntity<MarcaEntity> incluir(@RequestBody
    MarcaEntity Marca) {
        MarcaEntity novo = MarcaService.incluir(Marca);
        if (novo != null) {
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
 
     @PutMapping("/{id}")
    public ResponseEntity<MarcaEntity> editar(@PathVariable int id,
    @RequestBody MarcaEntity Marca) {
        MarcaEntity atualizado = MarcaService.editar(id,Marca);
        if (atualizado != null) {
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        MarcaService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

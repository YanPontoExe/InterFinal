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

import com.inter.demosca.Entities.FuncionarioEntity;
import com.inter.demosca.Services.FuncionarioService;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/Funcionario") //nomeando o path do Endpoint do controller, para ser executado no postman

public class FuncionarioController {
    private final FuncionarioService FuncionarioService;
 
    @GetMapping
    public ResponseEntity<List<FuncionarioEntity>> listarTodos() {
        List<FuncionarioEntity> lista = FuncionarioService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }
 
    @PostMapping
    public ResponseEntity<FuncionarioEntity> incluir(@RequestBody
    FuncionarioEntity Funcionario) {
        FuncionarioEntity novo = FuncionarioService.incluir(Funcionario);
        if (novo != null) {
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
 
     @PutMapping("/{id}")
    public ResponseEntity<FuncionarioEntity> editar(@PathVariable int id,
    @RequestBody FuncionarioEntity Funcionario) {
        FuncionarioEntity atualizado = FuncionarioService.editar(id,Funcionario);
        if (atualizado != null) {
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        FuncionarioService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

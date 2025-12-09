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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inter.demosca.DTO.RelatorioMovimentacaoDTO;
import com.inter.demosca.Entities.MovimentacaoEntity;
import com.inter.demosca.Services.MovimentacaoService;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/Movimentacao") //nomeando o path do Endpoint do controller, para ser executado no postman
 
public class MovimentacaoController {

    private final MovimentacaoService MovimentacaoService;
 
    @GetMapping("/relatorio") 
    public ResponseEntity<List<RelatorioMovimentacaoDTO>> relatorio(
        // @RequestParam(required = false) torna o parâmetro opcional
        @RequestParam(required = false) Integer idMaterial
    ) {
        // O Service recebe o ID (que pode ser null) e passa para o Repository
        List<RelatorioMovimentacaoDTO> lista = MovimentacaoService.listarTodas(idMaterial);
        
        return ResponseEntity.ok(lista);
    }

    @GetMapping
    public ResponseEntity<List<MovimentacaoEntity>> listarTodos() {
        List<MovimentacaoEntity> lista = MovimentacaoService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }
 
    @PostMapping
    public ResponseEntity<MovimentacaoEntity> incluir(@RequestBody
    MovimentacaoEntity Movimentacao) {
        MovimentacaoEntity novo = MovimentacaoService.incluir(Movimentacao);
        if (novo != null) {
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
 
     @PutMapping("/{id}")
    public ResponseEntity<MovimentacaoEntity> editar(@PathVariable int id,
    @RequestBody MovimentacaoEntity Movimentacao) {
        MovimentacaoEntity atualizado = MovimentacaoService.editar(id,Movimentacao);
        if (atualizado != null) {
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        MovimentacaoService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.inter.demosca.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inter.demosca.Entities.MovimentacaoEntity;
import com.inter.demosca.Repositories.MovimentacaoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovimentacaoService {
    private final MovimentacaoRepository MovimentacaoRepository;

     public MovimentacaoEntity incluir(MovimentacaoEntity Movimentacao) {

        return MovimentacaoRepository.save(Movimentacao);
    }
    public MovimentacaoEntity editar(int id, MovimentacaoEntity Movimentacao) {
        // Verifique se a Movimentacao existe
        Optional<MovimentacaoEntity> MovimentacaoExistente = 
        MovimentacaoRepository.findById(id);

        if (MovimentacaoExistente.isPresent()) {
            // Atualiza a Movimentacao
            MovimentacaoEntity MovimentacaoAtualizada = MovimentacaoExistente.get();
            MovimentacaoAtualizada.setCod_material(Movimentacao.getCod_material());  // Atualiza os campos necessários
            MovimentacaoAtualizada.setQuantidade(Movimentacao.getQuantidade());  // Atualiza os campos necessários
            MovimentacaoAtualizada.setSetor(Movimentacao.getSetor());  // Atualiza os campos necessários
            
            // Atualiza o usuário associado
            Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findById(Movimentacao.getUsuario().getCodFuncionario());
            if (usuarioOpt.isPresent()) {
                movimentacaoAtualizada.setUsuario(usuarioOpt.get());
            } else {
                throw new RuntimeException("Usuário não encontrado");
            }
            
            return MovimentacaoRepository.save(MovimentacaoAtualizada);  // Salva o Movimentacao atualizado
        } else {
            // Caso o Movimentacao não exista, retorna null
            return null;
        }
    }
    public List<MovimentacaoEntity> listarTodos() {
        return MovimentacaoRepository.findAll();
    }
    public void excluir(Integer id) {
        MovimentacaoRepository.deleteById(id);
}
}
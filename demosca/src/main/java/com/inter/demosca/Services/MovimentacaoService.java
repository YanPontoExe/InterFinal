package com.inter.demosca.Services;

import java.time.LocalDateTime;
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

    // Serviço procedure no sql mapeada
    public List<Object[]> getRelatorio(Integer id) {
        return MovimentacaoRepository.gerarRelatorio(id);
    }

    public MovimentacaoEntity editar(int id, MovimentacaoEntity Movimentacao) {
        // Verifique se a Movimentacao existe
        Optional<MovimentacaoEntity> MovimentacaoExistente = 
        MovimentacaoRepository.findById(id);

                if (MovimentacaoExistente.isPresent()) {
            MovimentacaoEntity movimentacaoAtualizada = MovimentacaoExistente.get();

            movimentacaoAtualizada.setCod_material(Movimentacao.getCod_material());
            movimentacaoAtualizada.setQuantidade(Movimentacao.getQuantidade());
            
            // Atualiza o usuário associado
            Optional<MovimentacaoEntity> MovimentacaoOpt = MovimentacaoRepository.findById(Movimentacao.getId_movimentacao());
            if (MovimentacaoOpt.isPresent()) {
                movimentacaoAtualizada.setId_movimentacao(MovimentacaoOpt.get().getId_movimentacao());
            } else {
                throw new RuntimeException("Usuário não encontrado");
            }

            return MovimentacaoRepository.save(movimentacaoAtualizada);
        } else {
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
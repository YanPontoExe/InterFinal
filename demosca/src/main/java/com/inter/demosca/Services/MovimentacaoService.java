package com.inter.demosca.Services;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inter.demosca.DTO.RelatorioMovimentacaoDTO;
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

        public List<RelatorioMovimentacaoDTO> listarTodas(Integer idMaterial) {
        List<Object[]> results = MovimentacaoRepository.listarMovimentacoes(idMaterial);

        return results.stream()
                      .map(r -> new RelatorioMovimentacaoDTO(
                          // Coluna 0: id_movimentacao (INT)
                          (Integer) r[0], 
                          // Coluna 1: quantidade (INT)
                          (Integer) r[1],
                          // Coluna 2: nome_material (VARCHAR/String)
                          (String) r[2],
                          // Coluna 3: usuario (VARCHAR/String) - Adicione verificação de null
                          r[3] != null ? (String) r[3] : null,
                          // Coluna 4: tipo_movimentacao (INT)
                          (String) r[4]
                      ))
                      .collect(Collectors.toList());
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
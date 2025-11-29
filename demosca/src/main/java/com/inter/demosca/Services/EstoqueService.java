package com.inter.demosca.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inter.demosca.Entities.EstoqueEntity;
import com.inter.demosca.Repositories.EstoqueRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstoqueService {
    private final EstoqueRepository EstoqueRepository;

     public EstoqueEntity incluir(EstoqueEntity Estoque) {

        return EstoqueRepository.save(Estoque);
    }
    public EstoqueEntity editar(int id, EstoqueEntity Estoque) {
        // Verifique se a Estoque existe
        Optional<EstoqueEntity> EstoqueExistente = 
        EstoqueRepository.findById(id);

        if (EstoqueExistente.isPresent()) {
            // Atualiza a Estoque
            EstoqueEntity EstoqueAtualizada = EstoqueExistente.get();
            EstoqueAtualizada.setCod_material(Estoque.getCod_material());
            EstoqueAtualizada.setQtd_atual(Estoque.getQtd_atual());
            EstoqueAtualizada.setQtd_max(Estoque.getQtd_max());
            EstoqueAtualizada.setQtd_min(Estoque.getQtd_min());
            
            return EstoqueRepository.save(EstoqueAtualizada);  // Salva o Estoque atualizado
        } else {
            // Caso o Estoque não exista, retorna null
            return null;
        }
    }
    public List<EstoqueEntity> listarTodos() {
        return EstoqueRepository.findAll();
    }
    public void excluir(Integer id) {
        EstoqueRepository.deleteById(id);
}

}

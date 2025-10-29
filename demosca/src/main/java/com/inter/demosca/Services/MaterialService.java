package com.inter.demosca.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inter.demosca.Entities.MaterialEntity;
import com.inter.demosca.Repositories.MaterialRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MaterialService {
    private final MaterialRepository MaterialRepository;

     public MaterialEntity incluir(MaterialEntity Material) {

        return MaterialRepository.save(Material);
    }
    public MaterialEntity editar(int id, MaterialEntity Material) {
        // Verifique se a Material existe
        Optional<MaterialEntity> MaterialExistente = 
        MaterialRepository.findById(id);
        
        if (MaterialExistente.isPresent()) {
            // Atualiza a Material
            MaterialEntity MaterialAtualizada = MaterialExistente.get();
            MaterialAtualizada.setDescricao(Material.getDescricao());  // Atualiza os campos necessários
            return MaterialRepository.save(MaterialAtualizada);  // Salva o Material atualizado
        } else {
            // Caso o Material não exista, retorna null
            return null;
        }
    }
    public List<MaterialEntity> listarTodos() {
        return MaterialRepository.findAll();
    }
    public void excluir(Integer id) {
        MaterialRepository.deleteById(id);
}
}

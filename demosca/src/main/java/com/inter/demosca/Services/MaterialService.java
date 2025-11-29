package com.inter.demosca.Services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    //ativação da função de total de movimentações do material no SQL Server 

    public Integer obterTotalMovimentacoes(Integer idMaterial) {
        return MaterialRepository.buscarTotalMovimentacoes(idMaterial);
    }
    
    public MaterialEntity editar(int id, MaterialEntity Material) {
        // Verifique se a Material existe
        Optional<MaterialEntity> MaterialExistente = 
        MaterialRepository.findById(id);
        
        if (MaterialExistente.isPresent()) {
            // Atualiza a Material
            MaterialEntity MaterialAtualizada = MaterialExistente.get();
            MaterialAtualizada.setDescricao(Material.getDescricao());  // Atualiza os campos necessários
            MaterialAtualizada.setMarca(Material.getMarca());  // Atualiza os campos necessários
            MaterialAtualizada.setCod_fornecedor(Material.getCod_fornecedor());  // Atualiza os campos necessários
            MaterialAtualizada.setDataCadastro(Material.getDataCadastro());  // Atualiza os campos necessários
            MaterialAtualizada.setStatus(Material.getStatus());  // Atualiza os campos necessários
            
            // Atualiza o usuário associado
            Optional<MaterialEntity> MaterialOpt = MaterialRepository.findById(Material.getId_material());
            if (MaterialOpt.isPresent()) {
                MaterialAtualizada.setId_material(MaterialOpt.get().getId_material());
            } else {
                throw new RuntimeException("Usuário não encontrado");
            }
            
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

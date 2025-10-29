package com.inter.demosca.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inter.demosca.Entities.FornecedorEntity;
import com.inter.demosca.Repositories.FornecedorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FornecedorService {
    private final FornecedorRepository FornecedorRepository;

     public FornecedorEntity incluir(FornecedorEntity Fornecedor) {

        return FornecedorRepository.save(Fornecedor);
    }
    public FornecedorEntity editar(int id, FornecedorEntity Fornecedor) {
        // Verifique se a Fornecedor existe
        Optional<FornecedorEntity> FornecedorExistente = 
        FornecedorRepository.findById(id);

        if (FornecedorExistente.isPresent()) {
            // Atualiza a Fornecedor
            FornecedorEntity FornecedorAtualizada = FornecedorExistente.get();
            FornecedorAtualizada.setDescricao(Fornecedor.getDescricao());  // Atualiza os campos necessários
            return FornecedorRepository.save(FornecedorAtualizada);  // Salva o Fornecedor atualizado
        } else {
            // Caso o Fornecedor não exista, retorna null
            return null;
        }
    }
    public List<FornecedorEntity> listarTodos() {
        return FornecedorRepository.findAll();
    }
    public void excluir(Integer id) {
        FornecedorRepository.deleteById(id);
}
}

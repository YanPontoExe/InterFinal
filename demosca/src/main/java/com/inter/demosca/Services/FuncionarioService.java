package com.inter.demosca.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inter.demosca.Entities.FuncionarioEntity;
import com.inter.demosca.Repositories.FuncionarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FuncionarioService {
    private final FuncionarioRepository FuncionarioRepository;

     public FuncionarioEntity incluir(FuncionarioEntity Funcionario) {

        return FuncionarioRepository.save(Funcionario);
    }
    public FuncionarioEntity editar(int id, FuncionarioEntity Funcionario) {
        // Verifique se a Funcionario existe
        Optional<FuncionarioEntity> FuncionarioExistente = 
        FuncionarioRepository.findById(id);

        if (FuncionarioExistente.isPresent()) {
            // Atualiza a Funcionario
            FuncionarioEntity FuncionarioAtualizada = FuncionarioExistente.get();
            FuncionarioAtualizada.setData_contratacao(Funcionario.getData_contratacao());  // Atualiza os campos necessários
            FuncionarioAtualizada.setNome_funcionario(Funcionario.getNome_funcionario());  // Atualiza os campos necessários
            FuncionarioAtualizada.setSetor(Funcionario.getSetor());  // Atualiza os campos necessários
            FuncionarioAtualizada.setStatus(Funcionario.getStatus());  // Atualiza os campos necessários
            FuncionarioAtualizada.setTurno(Funcionario.getTurno());  // Atualiza os campos necessários
            
            return FuncionarioRepository.save(FuncionarioAtualizada);  // Salva o Funcionario atualizado
        } else {
            // Caso o Funcionario não exista, retorna null
            return null;
        }
    }
    public List<FuncionarioEntity> listarTodos() {
        return FuncionarioRepository.findAll();
    }
    public void excluir(Integer id) {
        FuncionarioRepository.deleteById(id);
}
}

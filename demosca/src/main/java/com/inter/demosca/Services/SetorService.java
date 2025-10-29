package com.inter.demosca.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inter.demosca.Entities.SetorEntity;
import com.inter.demosca.Repositories.SetorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SetorService {
    private final SetorRepository SetorRepository;

     public SetorEntity incluir(SetorEntity Setor) {

        return SetorRepository.save(Setor);
    }
    public SetorEntity editar(int id, SetorEntity Setor) {
        // Verifique se a Setor existe
        Optional<SetorEntity> SetorExistente = 
        SetorRepository.findById(id);

        if (SetorExistente.isPresent()) {
            // Atualiza a Setor
            SetorEntity SetorAtualizada = SetorExistente.get();
            SetorAtualizada.setDescricao(Setor.getDescricao());
            
            return SetorRepository.save(SetorAtualizada);  // Salva o Setor atualizado
        } else {
            // Caso o Setor não exista, retorna null
            return null;
        }
    }
    public List<SetorEntity> listarTodos() {
        return SetorRepository.findAll();
    }
    public void excluir(Integer id) {
        SetorRepository.deleteById(id);
}
}

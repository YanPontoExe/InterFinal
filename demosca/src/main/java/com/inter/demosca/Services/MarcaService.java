package com.inter.demosca.Services;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inter.demosca.Entities.MarcaEntity;
import com.inter.demosca.Repositories.MarcaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarcaService {
    private final MarcaRepository MarcaRepository;

     public MarcaEntity incluir(MarcaEntity Marca) {

        return MarcaRepository.save(Marca);
    }
    public MarcaEntity editar(int id, MarcaEntity Marca) {
        // Verifique se a Marca existe
        Optional<MarcaEntity> MarcaExistente = 
        MarcaRepository.findById(id);

        if (MarcaExistente.isPresent()) {
            // Atualiza a Marca
            MarcaEntity MarcaAtualizada = MarcaExistente.get();
            MarcaAtualizada.setNome_marca(Marca.getNome_marca());  // Atualiza os campos necessários
            MarcaAtualizada.setPais_origem(Marca.getPais_origem());  // Atualiza os campos necessários
            MarcaAtualizada.setDescricao_marca(Marca.getDescricao_marca());  // Atualiza os campos necessários
            MarcaAtualizada.setDataCadastro(Marca.getDataCadastro());  // Atualiza os campos necessários
            MarcaAtualizada.setStatus(Marca.isStatus());  // Atualiza os campos necessários
            
            return MarcaRepository.save(MarcaAtualizada);  // Salva o Marca atualizado
        } else {
            // Caso o Marca não exista, retorna null
            return null;
        }
    }
    public List<MarcaEntity> listarTodos() {
        return MarcaRepository.findAll();
    }
    public void excluir(Integer id) {
        MarcaRepository.deleteById(id);
}
}

package com.inter.demosca.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inter.demosca.Entities.EntradaEntity;
import com.inter.demosca.Repositories.EntradaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntradaService {
    private final EntradaRepository EntradaRepository;

     public EntradaEntity incluir(EntradaEntity Entrada) {

        return EntradaRepository.save(Entrada);
    }
    public EntradaEntity editar(int id, EntradaEntity Entrada) {
        // Verifique se a Entrada existe
        Optional<EntradaEntity> EntradaExistente = 
        EntradaRepository.findById(id);

        if (EntradaExistente.isPresent()) {
            // Atualiza a Entrada
            EntradaEntity EntradaAtualizada = EntradaExistente.get();
            EntradaAtualizada.setCod_fornecedor(Entrada.getCod_fornecedor());
            EntradaAtualizada.setNotaFiscal(Entrada.getNotaFiscal());
            EntradaAtualizada.setDataEntrada(Entrada.getDataEntrada());
            
            return EntradaRepository.save(EntradaAtualizada);  // Salva o Entrada atualizado
        } else {
            // Caso o Entrada não exista, retorna null
            return null;
        }
    }
    public List<EntradaEntity> listarTodos() {
        return EntradaRepository.findAll();
    }
    public void excluir(Integer id) {
        EntradaRepository.deleteById(id);
}

}

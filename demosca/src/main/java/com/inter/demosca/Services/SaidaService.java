package com.inter.demosca.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inter.demosca.Entities.SaidaEntity;
import com.inter.demosca.Repositories.SaidaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaidaService {
private final SaidaRepository SaidaRepository;

     public SaidaEntity incluir(SaidaEntity Saida) {

        return SaidaRepository.save(Saida);
    }

    // Mapeia a function SQL de Listar todas as Saidas
    public List<SaidaEntity> listarTodas() {
        return SaidaRepository.listarSaidas();
    }

    public SaidaEntity editar(int id, SaidaEntity Saida) {
        // Verifique se a Saida existe
        Optional<SaidaEntity> SaidaExistente = 
        SaidaRepository.findById(id);

        if (SaidaExistente.isPresent()) {
            // Atualiza a Saida
            SaidaEntity SaidaAtualizada = SaidaExistente.get();
            SaidaAtualizada.setCod_funcionario(Saida.getCod_funcionario());
            SaidaAtualizada.setMotivo(Saida.getMotivo());
            SaidaAtualizada.setData_saida(Saida.getData_saida());

            return SaidaRepository.save(SaidaAtualizada);  // Salva o Saida atualizado
        } else {
            // Caso o Saida não exista, retorna null
            return null;
        }
    }
    public List<SaidaEntity> listarTodos() {
        return SaidaRepository.findAll();
    }
    public void excluir(Integer id) {
        SaidaRepository.deleteById(id);
}
}

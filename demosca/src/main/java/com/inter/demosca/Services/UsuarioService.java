package com.inter.demosca.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inter.demosca.Entities.UsuarioEntity;
import com.inter.demosca.Repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository UsuarioRepository;

     public UsuarioEntity incluir(UsuarioEntity Usuario) {

        return UsuarioRepository.save(Usuario);
    }
    public UsuarioEntity editar(int id, UsuarioEntity Usuario) {
        // Verifique se a Usuario existe
        Optional<UsuarioEntity> UsuarioExistente = 
        UsuarioRepository.findById(id);

        if (UsuarioExistente.isPresent()) {
            // Atualiza a Usuario
            UsuarioEntity UsuarioAtualizada = UsuarioExistente.get();
            UsuarioAtualizada.setCod_funcionario(Usuario.getCod_funcionario());
            UsuarioAtualizada.setUser(Usuario.getUser());
            UsuarioAtualizada.setSenha(Usuario.getSenha());
            UsuarioAtualizada.setStatus(Usuario.getStatus());
            UsuarioAtualizada.setTipo_usuario(Usuario.getTipo_usuario());
            
            return UsuarioRepository.save(UsuarioAtualizada);  // Salva o Usuario atualizado
        } else {
            // Caso o Usuario não exista, retorna null
            return null;
        }
    }
    public List<UsuarioEntity> listarTodos() {
        return UsuarioRepository.findAll();
    }
    public void excluir(Integer id) {
        UsuarioRepository.deleteById(id);
}
}

package com.inter.demosca.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.inter.demosca.Entities.UsuarioEntity;
import com.inter.demosca.Repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository; 

    public UsuarioEntity incluir(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }

    public UsuarioEntity editar(int id, UsuarioEntity usuario) {
        Optional<UsuarioEntity> usuarioExistente = 
        usuarioRepository.findById(id);

        if (usuarioExistente.isPresent()) {
            UsuarioEntity usuarioAtualizada = usuarioExistente.get();
            usuarioAtualizada.setUsername(usuario.getUsername());
            usuarioAtualizada.setPassword(usuario.getPassword());
            
            return usuarioRepository.save(usuarioAtualizada);
        } else {
            return null;
        }
    }
    public List<UsuarioEntity> listarTodos() {
        return usuarioRepository.findAll();
    }
    public void excluir(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public boolean validar(String username, String password) {
        UsuarioEntity user = usuarioRepository.findByUsername(username);

        if (user == null) return false;

        return passwordEncoder.matches(password, user.getPassword());
    }
}
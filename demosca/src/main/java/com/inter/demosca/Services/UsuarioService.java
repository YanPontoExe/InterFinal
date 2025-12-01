package com.inter.demosca.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.inter.demosca.Entities.UsuarioEntity;
import com.inter.demosca.Repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
     @Autowired
    private UsuarioRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    public void salvar(String username, String senhaPura) {

        UsuarioEntity u = new UsuarioEntity();
        u.setUsuario(username);
        u.setSenha(encoder.encode(senhaPura)); // <<< AQUI CRIPTOGRAFA

        repo.save(u);
    }

}

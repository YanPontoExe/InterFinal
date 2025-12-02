package com.inter.demosca.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inter.demosca.Entities.UsuarioEntity;
import com.inter.demosca.Repositories.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService{
    @Autowired
    private UsuarioRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity u = repo.findByUsername(username);

        if (u == null) {
        throw new UsernameNotFoundException("Usuário não encontrado");
        }


        return User
                .withUsername(u.getUsername())
                .password(u.getPassword())      // tem que estar CRIPTOGRAFADA
                .roles("USER")
                .build();
    }
}

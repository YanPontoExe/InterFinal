package com.inter.demosca.WebSecurityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inter.demosca.Entities.UsuarioEntity;
import com.inter.demosca.Repositories.UsuarioRepository;

@Primary
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    UsuarioEntity user = usuarioRepository.findByUsername(username);

    if (user == null) {
        throw new UsernameNotFoundException("Usuário não encontrado");
    }

    return org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password(user.getPassword())
            .roles("USER")
            .build();
}

}



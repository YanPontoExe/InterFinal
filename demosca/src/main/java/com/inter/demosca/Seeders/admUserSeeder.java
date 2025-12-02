package com.inter.demosca.Seeders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.inter.demosca.Entities.UsuarioEntity;
import com.inter.demosca.Repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class admUserSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        if(usuarioRepository.count() == 0){
            UsuarioEntity admin = new UsuarioEntity();
            admin.setUsername("Administrador");
            admin.setPassword(passwordEncoder.encode("admin123"));
            usuarioRepository.save(admin);
        }
    }

}

package com.inter.demosca.WebSecurityConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigWeb {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/", 
                    "/login",          // permite acesso à sua página de login
                    "/css/**", 
                    "/js/**", 
                    "/images/**"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")                 // sua página GET
                .loginProcessingUrl("/process-login") // endpoint POST tratado pelo Spring
                .defaultSuccessUrl("/", true)   // página após login
                .permitAll()
            );

        return http.build();
    }
}


    

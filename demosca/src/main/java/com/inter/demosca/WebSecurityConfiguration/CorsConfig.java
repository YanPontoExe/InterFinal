package com.inter.demosca.WebSecurityConfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
    public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Mapeia todos os endpoints /Controllers/**
                .allowedOrigins("http://localhost:8080", "http://localhost:8081", "http://localhost:8082", "https://estokar-pastel-5mn35h44g-yan-henrrys-projects.vercel.app/") // Permite o domínio do frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}

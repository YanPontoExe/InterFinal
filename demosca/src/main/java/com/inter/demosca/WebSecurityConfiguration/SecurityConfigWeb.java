package com.inter.demosca.WebSecurityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Novos Imports necessários para a configuração do CORS
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;
import java.util.List;
// import org.springframework.security.config.Customizer; // Não é necessário com o source explícito

@Configuration
@EnableWebSecurity
public class SecurityConfigWeb {


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 🔑 PASSO 1: INTEGRAÇÃO DO CORS NA CADEIA DE SEGURANÇA.
            // Isso garante que o filtro CORS seja executado ANTES do filtro de Autorização/Autenticação.
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            
            // Desabilita CSRF (Comum em APIs REST)
            .csrf(csrf -> csrf.disable())
            
            // Configura o provedor de autenticação (mantido)
            .authenticationProvider(authProvider())
            
            // Configura as regras de autorização
            .authorizeHttpRequests(auth -> auth
                
                // ✅ Permite todas as requisições OPTIONS (CORS preflight)
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/Material").permitAll()
                
                // ✅ Permite acesso a todas as rotas públicas listadas
                .requestMatchers(
                    "/**", 
                    "/css/**", 
                    "/js/**", 
                    "/images/**",
                    "/cadastro", 
                    "/cadastrar", 
                    "/Usuario/auth", 
                    "/Usuarios",
                    "/Usuario",
                    "/Dashboard/stats", 
                    "/Dashboard/stats/**", 
                    "/Marcas", 
                    "/api/login", 
                    "Material",
                    "api/Usuario"
                ).permitAll()
                
                // 🔒 Qualquer outra requisição deve ser autenticada
                .anyRequest().authenticated()
            )
            
            // Desabilita o formulário de login padrão (mantido)
            .formLogin(form -> form.disable());
                
        return http.build();
    }
    
    // 🔑 PASSO 2: Bean que define as regras do CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // 🚨 IMPORTANTE: Permite a origem do seu Front-end (8081)
        configuration.setAllowedOrigins(List.of("http://localhost:8081")); 
        
        // Permite todos os métodos necessários, incluindo OPTIONS, POST e GET
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // Permite todos os headers (necessário para Content-Type e Authorization)
        configuration.setAllowedHeaders(List.of("*")); 
        
        // Necessário se você usa cookies ou Authorization headers
        configuration.setAllowCredentials(true); 

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Aplica a todas as rotas
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
    
}
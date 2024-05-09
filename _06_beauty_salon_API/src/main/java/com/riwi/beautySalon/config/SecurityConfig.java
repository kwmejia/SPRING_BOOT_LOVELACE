package com.riwi.beautySalon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //Activar spring security en esta clase
public class SecurityConfig {

    //Crear rutas publicas
    private final String[] PUBLIC_RESOURCES  = { "/services/public/get","/auth/**" };
    
    /**
     * 
     * @throws Exception 
     * @Bean Anotation: Esta anotación le indica a Spring boot  el objeto retornado
     * por el metodo debe ser registrado como un bean en el contexto de spring (lata)
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http
                .csrf(csrf -> csrf.disable()) //Desabilitar protección csrf -> Statelest
                .authorizeHttpRequests(authRequest -> authRequest
                    .requestMatchers(PUBLIC_RESOURCES).permitAll() //Configurar rutas publicas
                    .anyRequest().authenticated() 
                ).build();

    }
}

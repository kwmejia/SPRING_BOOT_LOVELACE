package com.riwi.beautySalon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.riwi.beautySalon.infraestructure.helpers.JwtFilter;
import com.riwi.beautySalon.utils.enums.Role;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity //Activar spring security en esta clase
@AllArgsConstructor
public class SecurityConfig {

    @Autowired
    private final AuthenticationProvider authenticationProvider;
    @Autowired
    private final JwtFilter jwtFilter;

    //Crear rutas publicas
    private final String[] PUBLIC_RESOURCES  = { "/services/public/get","/auth/**" };

    private final String[] ADMIN_RESOURCES = { "register/employee"};
    
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
                    .requestMatchers(ADMIN_RESOURCES).hasAuthority(Role.ADMIN.name())
                    .requestMatchers(PUBLIC_RESOURCES).permitAll() //Configurar rutas publicas
                    .anyRequest().authenticated() 
                )
                .sessionManagement(sessionManager -> 
                    sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //Agregarmos el proveedor de autenticación
                .authenticationProvider(authenticationProvider)
                //Agregar el filtro personalizado antes del filtro de spring security
                .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}

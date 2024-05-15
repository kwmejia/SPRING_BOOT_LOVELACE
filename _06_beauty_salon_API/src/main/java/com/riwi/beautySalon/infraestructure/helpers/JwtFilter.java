package com.riwi.beautySalon.infraestructure.helpers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

/*OncePerRequestFilter Extendemos de esta clase para crear un filtro personalizado */
@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //1. Obtener el token del request
        String token = this.getTokenFromRequest(request);
        
        //2. Si el token es nulo entonces que siga con los filtros de spring security
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        //3. Sacar el username del token
        String userName = this.jwtService.getUsernameFromToken(token);

        //4. Si no lo encuentra en el contexto de spring
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            //5. Obtener la información del usuario
            UserDetails user = userDetailsService.loadUserByUsername(userName);
            
            //6. Si el token es valido
            if (this.jwtService.isTokeValid(token, user)) {
                
                //Creamos la autenticación y la registramos  en el contexto de spring

                var authToken =  new UsernamePasswordAuthenticationToken(
                    userName,
                    null,
                    user.getAuthorities()
                );

                /*
                 * setDetails: Esteblece  detalles adicionales  de la autenticacion, como la direccion ip  y la sesión de donde se realiza la solucitud
                 */
                authToken.setDetails(new WebAuthenticationDetails(request));

                /*Guardar la autenticación en el contexto de spring */
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }

    public String getTokenFromRequest(HttpServletRequest request){
       final String token = request.getHeader(HttpHeaders.AUTHORIZATION);

       //SI el token tiene una longitud y ademas comienza con la palabra Bearer
       if (StringUtils.hasLength(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
       }
        return null;
    }


    
}

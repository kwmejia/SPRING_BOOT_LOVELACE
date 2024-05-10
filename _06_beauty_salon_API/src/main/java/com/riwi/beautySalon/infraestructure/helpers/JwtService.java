package com.riwi.beautySalon.infraestructure.helpers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.riwi.beautySalon.domain.entities.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
    //1. Crear la firma o clave
    private final String SECRET_KEY = "bWkgc3VwZXIgY2xhdmUgc2VjcmV0YSBzZWNyZXRhIHNlY3JldGEsIG1pIHN1cGVyIGNsYXZlIHNlY3JldGEgc2VjcmV0YSBzZWNyZXRh";

    //2. MÉTODO PARA ENCRIPTAR LA CLAVE SECRETA
    public SecretKey getKey(){
        //Convertir la llave a bytes
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        //Retornamos la llave cifrada
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //3. CONSTRUIR EL JWT
    public String getToken(Map<String,Object> claims, User user){

        return Jwts.builder()
                .claims(claims) //Agrego el cuerpo del jwt
                .subject(user.getUsername()) //Agrego de quien es el jwt
                .issuedAt(new Date(System.currentTimeMillis())) //Fecha de creación
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) //Fecha de expiración
                .signWith(this.getKey()) //firmar el token
                .compact();
    }
    //4. Método para obtener el jwt

    public String getToken(User user){
        //Crear el map de claims
        Map<String,Object> claims = new HashMap<>();
        
        claims.put("id", user.getId());
        claims.put("role", user.getRole().name());

        return getToken(claims, user);
    }
}

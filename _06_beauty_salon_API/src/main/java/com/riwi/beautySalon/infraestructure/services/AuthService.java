package com.riwi.beautySalon.infraestructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riwi.beautySalon.api.dto.request.LoginReq;
import com.riwi.beautySalon.api.dto.request.RegisterReq;
import com.riwi.beautySalon.api.dto.response.AuthResp;
import com.riwi.beautySalon.domain.entities.User;
import com.riwi.beautySalon.domain.repositories.UserRepository;
import com.riwi.beautySalon.infraestructure.abstract_service.IAuthService;
import com.riwi.beautySalon.infraestructure.helpers.JwtService;
import com.riwi.beautySalon.utils.enums.Role;
import com.riwi.beautySalon.utils.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final JwtService jwtService;

    @Override
    public AuthResp login(LoginReq request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public AuthResp register(RegisterReq request) {
        /*1. Validar que el usuario no existe */
        User exist = this.findUser(request.getUserName());

        if (exist != null) {
            throw new BadRequestException("El usuario ya existe");
        }

        /*Construir el usuario */
        User user = User.builder()
                .userName(request.getUserName())
                .password(request.getPassword())
                .role(Role.CLIENT)
                .build();

        /** Guardamos el user en la db */
        user = this.userRepository.save(user);

        return AuthResp.builder()
                .message("Registro completado exitosamente")
                .token(this.jwtService.getToken(user))
                .build();

    }
    
    private User findUser(String username){
        return this.userRepository.findByUserName(username)
                .orElse(null);
    }
}

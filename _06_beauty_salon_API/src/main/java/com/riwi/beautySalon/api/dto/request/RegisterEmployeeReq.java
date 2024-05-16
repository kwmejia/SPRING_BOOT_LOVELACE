package com.riwi.beautySalon.api.dto.request;

import com.riwi.beautySalon.utils.enums.RoleEmployee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper=true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterEmployeeReq extends RegisterReq{
    
    @NotBlank(message = "El nombre es requerido")
    @Size(
        min = 1, 
        max = 100, 
        message = "El nombre debe tener entre 1 y 100 caracteres"
    )
    private String firstName;
    @NotBlank(message = "El apellido es requerido")
    @Size(
        min = 1, 
        max = 100, 
        message = "El apellido debe tener entre 1 y 100 caracteres"
    )
    private String lastName;
    @Size(
        min = 1, 
        max = 100, 
        message = "El email debe tener entre 1 y 100 caracteres"
    )
    @Email
    private String email;
    @Size(
        min = 10, 
        max = 20, 
        message = "El teléfono debe tener entre 10 y 20 caracteres"
    )
    private String phone;
    
    @NotNull( message = "El rol es requerido")
    private RoleEmployee role;
}

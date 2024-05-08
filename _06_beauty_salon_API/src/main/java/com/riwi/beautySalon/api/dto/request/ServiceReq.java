package com.riwi.beautySalon.api.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceReq {
    @NotBlank( message = "El nombre es requerido")
    @Size(min = 0, max = 100)
    private String name;
    private String description;
    @NotNull(message = "El precio es requerido")
    @DecimalMin( 
        value = "0.01",
        message = "El valor del servicio debe ser mayor a 0"
    )
    private BigDecimal price;
}

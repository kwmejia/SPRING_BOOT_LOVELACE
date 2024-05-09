package com.riwi.beautySalon.api.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentReq {
    
    @FutureOrPresent(message = "La fecha y hora debe ser futura")
    @NotNull(message = "La fecha y hora de la cita es requeridas")
    private LocalDateTime dateTime;
    @Min(value = 5)
    @Max(value = 760)
    @NotNull(message = "La duración es requerida")
    private Integer duration;
    private String comments;
    @NotNull(message = "El id del cliente es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor a cero ")
    private Long clientId;
    @NotNull(message = "El id del servicio es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor a cero")
    private Long serviceId;
    @NotNull(message = "El id del empleado es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor a cero")
    private Long employeeId;
}


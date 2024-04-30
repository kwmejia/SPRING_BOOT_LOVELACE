package com.riwi.vacants.utils.dto.errors;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
/* SuperBuilder crea un constructor con el super de Serializable */
@SuperBuilder
public class BaseErrorResponse implements Serializable {
    private String status;
    private Integer code;
}

package com.riwi.beautySalon.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientBasicResp {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;  
}

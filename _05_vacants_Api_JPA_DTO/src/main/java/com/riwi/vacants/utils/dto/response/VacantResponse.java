package com.riwi.vacants.utils.dto.response;

import com.riwi.vacants.entities.Company;
import com.riwi.vacants.utils.enums.StateVacant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VacantResponse {
    private Long id;
    private String title;
    private String description;
    private StateVacant status;
    private CompanyResponse company;
}

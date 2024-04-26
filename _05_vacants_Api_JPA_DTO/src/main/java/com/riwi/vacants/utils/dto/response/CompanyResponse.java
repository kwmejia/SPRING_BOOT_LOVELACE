package com.riwi.vacants.utils.dto.response;

import java.util.List;

import com.riwi.vacants.entities.Vacant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponse {
    private String id;
    private String name;
    private String location;
    private String contact;
    private List<Vacant> vacants;
}

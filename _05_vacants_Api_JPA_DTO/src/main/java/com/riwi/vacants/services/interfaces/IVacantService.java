package com.riwi.vacants.services.interfaces;

import com.riwi.vacants.utils.dto.request.VacantRequest;
import com.riwi.vacants.utils.dto.response.VacantResponse;

public interface IVacantService
        extends CrudService<VacantRequest, VacantResponse, Long> {

    public VacantResponse getById(Long id);
}

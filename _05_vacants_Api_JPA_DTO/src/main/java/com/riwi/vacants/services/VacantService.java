package com.riwi.vacants.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.vacants.entities.Company;
import com.riwi.vacants.entities.Vacant;
import com.riwi.vacants.repositories.CompanyRepository;
import com.riwi.vacants.repositories.VacantRepository;
import com.riwi.vacants.services.interfaces.IVacantService;
import com.riwi.vacants.utils.dto.request.VacantRequest;
import com.riwi.vacants.utils.dto.response.CompanyToVacantResponse;
import com.riwi.vacants.utils.dto.response.VacantResponse;
import com.riwi.vacants.utils.enums.StateVacant;
import com.riwi.vacants.utils.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VacantService implements IVacantService {

    @Autowired
    private final VacantRepository vacantRepository;
    @Autowired
    private final CompanyRepository companyRepository;

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public VacantResponse create(VacantRequest request) {
        // Nos aseguramos que el id de la compañia dentro del request
        // sea válido
        Company company = this.companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new IdNotFoundException("company"));

        // Convertimos el request a la entidad
        Vacant vacant = this.requestToVacant(request, new Vacant());

        // Agregamos la empresa encontrada anteriormente
        vacant.setCompany(company);

        // Guardar en la db y conver al dto de respuesta
        return this.entityToResponse(this.vacantRepository.save(vacant));

    }

    @Override
    public VacantResponse update(Long id, VacantRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Page<VacantResponse> getAll(int page, int size) {
        if (page < 0)
            page = 0;

        // Creamos la paginacion
        PageRequest pagination = PageRequest.of(page, size);

        /*
         * Obtenemos todas las vacantes de la BD y las mapeamos al dto de respuesta
         */
        return this.vacantRepository.findAll(pagination)
                .map(vacant -> this.entityToResponse(vacant));
    }

    @Override
    public VacantResponse getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    private VacantResponse entityToResponse(Vacant vacant) {
        // Crear la instancia de la respuesta
        VacantResponse response = new VacantResponse();

        // Copiamos las propiedades de de la entidad al dto de respuesta
        BeanUtils.copyProperties(vacant, response);

        // Crear la instacia de dto de compañia dentro vacante
        CompanyToVacantResponse companyResp = new CompanyToVacantResponse();

        // Copio las propiedades de la entidad en el dto de respuesta
        BeanUtils.copyProperties(vacant.getCompany(), companyResp);

        // Agregamos el dto de respuesta de la compañia en la respuesta general
        response.setCompany(companyResp);
        return response;
    }

    private Vacant requestToVacant(VacantRequest request, Vacant entity) {

        entity.setDescription(request.getDescription());
        entity.setTitle(request.getTitle());
        entity.setStatus(StateVacant.ACTIVE);

        return entity;
    }
}

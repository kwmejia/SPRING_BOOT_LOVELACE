package com.riwi.vacants.services;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.vacants.entities.Company;
import com.riwi.vacants.entities.Vacant;
import com.riwi.vacants.repositories.CompanyRepository;
import com.riwi.vacants.services.interfaces.ICompanyService;
import com.riwi.vacants.utils.dto.request.CompanyRequest;
import com.riwi.vacants.utils.dto.response.CompanyResponse;
import com.riwi.vacants.utils.dto.response.VacantToCompanyResponse;
import com.riwi.vacants.utils.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompanyService implements ICompanyService {

    @Autowired
    private final CompanyRepository companyRepository;

    @Override
    public void delete(String id) {
        // Busco la compañia a la que corresponde el ID
        Company company = this.find(id);
        // Elimino
        this.companyRepository.delete(company);
    }

    @Override
    public CompanyResponse create(CompanyRequest request) {
        Company company = this.requestToCompany(request, new Company());

        return this.entityToResponse(this.companyRepository.save(company));
    }

    @Override
    public CompanyResponse update(String id, CompanyRequest request) {
        // Buscamos la compañia con el id
        Company company = this.find(id);
        // Llenamos la compañia con los nuevos datos a la vez que lo convertimos en
        // entidad
        Company companyUpdate = this.requestToCompany(request, company);

        // Guardamos la compañia actualizada y convertimos la respuesta
        return this.entityToResponse(this.companyRepository.save(companyUpdate));
    }

    @Override
    public Page<CompanyResponse> getAll(int page, int size) {
        if (page < 0) {
            page = 0;
        }

        PageRequest pagination = PageRequest.of(page, size);

        /**
         * Iteramos con map cada compañia y la convertimos
         * -> podemos hacerlo con expresión lambda flecha ->
         * o Expresión lamda inferencenal ::
         */
        // return this.companyRepository.findAll(pagination)
        // .map(company -> this.entityToResponse(company));

        return this.companyRepository.findAll(pagination)
                .map(this::entityToResponse);
    }

    @Override
    public CompanyResponse getById(String id) {
        return this.entityToResponse(this.find(id));
    }

    private Company find(String id) {
        return this.companyRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Company"));
    }

    /**
     * Este método se encarga de convertir un objeto Company a CompanyResponse
     */
    private CompanyResponse entityToResponse(Company entity) {
        CompanyResponse response = new CompanyResponse();

        BeanUtils.copyProperties(entity, response);

        /**
         * Mapeamos las vacantes convirtiendo cada una de ellas a al dto de respuesta
         * stream() Convierte una lista en una colección para poder acceder a los
         * métodos
         * map, for arch etc ..
         * .collect(Collectors.toList()) Convierte la colección de nuevo a lista
         */
        response.setVacants(entity.getVacants()
                .stream().map(vacant -> this.vacantToResponse(vacant))
                .collect(Collectors.toList()));

        return response;
    }

    private VacantToCompanyResponse vacantToResponse(Vacant entity) {
        VacantToCompanyResponse response = new VacantToCompanyResponse();
        BeanUtils.copyProperties(entity, response);

        return response;
    }

    private Company requestToCompany(CompanyRequest request, Company company) {
        // Hacemos la copia
        company.setContact(request.getContact());
        company.setName(request.getName());
        company.setLocation(request.getLocation());
        company.setVacants(new ArrayList<>());
        return company;
    }

}

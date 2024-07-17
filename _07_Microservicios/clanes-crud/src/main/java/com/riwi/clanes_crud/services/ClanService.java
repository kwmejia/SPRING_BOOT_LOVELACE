package com.riwi.clanes_crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.clanes_crud.dto.request.ClanGetRequest;
import com.riwi.clanes_crud.dto.request.ClanRequest;
import com.riwi.clanes_crud.dto.request.ClanUpdataRequest;
import com.riwi.clanes_crud.entities.Clan;
import com.riwi.clanes_crud.repositories.ClanRepository;
import com.riwi.clanes_crud.repositories.CohortRepository;
import com.riwi.clanes_crud.services.abstract_service.IClanService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ClanService implements IClanService {
    
    @Autowired
    private final ClanRepository clanRepository;
    @Autowired
    private final CohortRepository  cohortRepository;

    @Override
    public Page<Clan> getClans(ClanGetRequest request) {
       if (request.getPage() <0) request.setPage(1);
        
        PageRequest pagination = PageRequest.of(request.getPage(), request.getSize());

        log.info("Getting clans with request: {}", request);
       return this.clanRepository.getAll(
            request.getName(),
            request.getDescription(),
            request.getIsActive(),
            request.getCohortId(),
            pagination
       );
    }

    @Override
    public Clan create(ClanRequest clan) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Clan update(ClanUpdataRequest clan, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Clan disable(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'disable'");
    }

    
}

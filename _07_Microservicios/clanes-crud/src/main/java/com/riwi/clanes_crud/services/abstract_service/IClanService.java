package com.riwi.clanes_crud.services.abstract_service;

import org.springframework.data.domain.Page;

import com.riwi.clanes_crud.dto.request.ClanGetRequest;
import com.riwi.clanes_crud.dto.request.ClanRequest;
import com.riwi.clanes_crud.dto.request.ClanUpdataRequest;
import com.riwi.clanes_crud.entities.Clan;

public interface IClanService {
    public Page<Clan> getClans(ClanGetRequest request);
    public Clan create(ClanRequest clan);
    public Clan update(ClanUpdataRequest clan, Long id);
    public Clan disable(Long id);
}
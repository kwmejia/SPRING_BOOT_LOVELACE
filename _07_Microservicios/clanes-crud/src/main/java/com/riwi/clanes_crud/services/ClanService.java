package com.riwi.clanes_crud.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.clanes_crud.dto.request.ClanGetRequest;
import com.riwi.clanes_crud.dto.request.ClanRequest;
import com.riwi.clanes_crud.dto.request.ClanUpdataRequest;
import com.riwi.clanes_crud.entities.Clan;
import com.riwi.clanes_crud.entities.Cohort;
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
    private final CohortRepository cohortRepository;

    @Override
    public Page<Clan> getClans(ClanGetRequest request) {
        if (request.getPage() < 0)
            request.setPage(1);

        PageRequest pagination = PageRequest.of(request.getPage(), request.getSize());

        log.info("Getting clans with request: {}", request);
        return this.clanRepository.getAll(
                request.getName(),
                request.getDescription(),
                request.getIsActive(),
                request.getCohortId(),
                pagination);
    }

    @Override
    public Clan create(ClanRequest clan) {
        Cohort cohort = this.cohortRepository.findById(clan.getCohortId())
                .orElseThrow(() -> new RuntimeException("Cohort not found"));

        Clan newClan = Clan.builder()
                .name(clan.getName())
                .description(clan.getDescription())
                .cohort(cohort)
                .build();

        log.info("Creating new clan: {}", newClan);

        return this.clanRepository.save(newClan);
    }

    @Override
    public Clan update(ClanUpdataRequest clan, Long id) {
        Clan oldClan = this.clanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clan not found"));

        if (clan.getCohortId() != oldClan.getCohort().getId()) {

            Cohort cohort = this.cohortRepository.findById(clan.getCohortId())
                    .orElseThrow(() -> new RuntimeException("Cohort not found"));

            oldClan.setCohort(cohort);
        }

        oldClan.setName(clan.getName());
        oldClan.setDescription(clan.getDescription());
        oldClan.setUpdatedAt(LocalDateTime.now());
      

        log.info("Updating clan: {}", oldClan);

        return this.clanRepository.save(oldClan);
    }

    @Override
    public Clan disable(Long id) {
        Clan clan = this.clanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clan not found"));

        clan.setIsActive(false);
        clan.setUpdatedAt(LocalDateTime.now());

        log.info("Disabling clan: {}", clan);

        return this.clanRepository.save(clan);
    }

}

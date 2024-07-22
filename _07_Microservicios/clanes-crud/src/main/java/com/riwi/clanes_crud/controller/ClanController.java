package com.riwi.clanes_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.clanes_crud.dto.request.ClanGetRequest;
import com.riwi.clanes_crud.dto.request.ClanRequest;
import com.riwi.clanes_crud.dto.request.ClanUpdataRequest;
import com.riwi.clanes_crud.entities.Clan;
import com.riwi.clanes_crud.services.abstract_service.IClanService;

import lombok.AllArgsConstructor;

@RequestMapping("/clans")
@RestController
@AllArgsConstructor
public class ClanController {
    @Autowired
    private final IClanService clanService;

    @GetMapping
    public ResponseEntity<Page<Clan>> getClans(
        @RequestParam(defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "5") Integer size,
        @RequestParam(defaultValue = "") String name,
        @RequestParam(defaultValue = "") String description,
        @RequestParam(required = false) Boolean isActive,
        @RequestParam(required = false) Long cohortId
    ) {

        ClanGetRequest request = ClanGetRequest.builder()
            .page(page-1)
            .size(size)
            .name(name)
            .description(description)
            .isActive(isActive)
            .cohortId(cohortId)
            .build();

        System.out.println(request);

        return ResponseEntity.ok(this.clanService.getClans(request));
    }

    @PostMapping
    public ResponseEntity<Clan> createClan(
        @RequestBody ClanRequest clan
    ) {
        return ResponseEntity.ok(this.clanService.create(clan));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clan> updateClan(
        @RequestBody ClanUpdataRequest clan,
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(this.clanService.update(clan, id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Clan> updateClanPatch(
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(this.clanService.disable(id));
    }
}

package com.riwi.clanes_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.clanes_crud.dto.request.ClanGetRequest;
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
            .page(page)
            .size(size)
            .name(name)
            .description(description)
            .isActive(isActive)
            .cohortId(cohortId)
            .build();

        return ResponseEntity.ok(this.clanService.getClans(request));
    }

}

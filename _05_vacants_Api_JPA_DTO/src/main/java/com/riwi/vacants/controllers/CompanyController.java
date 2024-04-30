package com.riwi.vacants.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.vacants.services.interfaces.ICompanyService;
import com.riwi.vacants.utils.dto.request.CompanyRequest;
import com.riwi.vacants.utils.dto.response.CompanyResponse;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {
    @Autowired
    private final ICompanyService companyService;

    @GetMapping
    public ResponseEntity<Page<CompanyResponse>> get(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size) {

        return ResponseEntity.ok(this.companyService.getAll(page - 1, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CompanyResponse> getById(
            @PathVariable String id) {
        return ResponseEntity.ok(this.companyService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> insert(
            @RequestBody CompanyRequest company) {
        return ResponseEntity.ok(this.companyService.create(company));
    }

}

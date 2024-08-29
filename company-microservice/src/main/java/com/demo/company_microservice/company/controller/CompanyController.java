package com.demo.company_microservice.company.controller;

import com.demo.company_microservice.company.model.Company;
import com.demo.company_microservice.company.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@Validated
@RequestMapping("/api/v1/companies")
public class CompanyController {

    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        return companyService.createCompany(company);
    }

    @GetMapping
    public ResponseEntity<List<Company>> findAllJobs() {
        return companyService.findAllJobs();
    }

    @GetMapping("{companyId}")
    public ResponseEntity<Company> findById(@PathVariable Long companyId) {
        return companyService.findById(companyId);
    }

    @PutMapping("{companyId}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long companyId, @RequestBody Company company) {
        return companyService.updateCompany(companyId, company);
    }

    @DeleteMapping("{companyId}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long companyId) {
        return companyService.deleteCompany(companyId);
    }

}

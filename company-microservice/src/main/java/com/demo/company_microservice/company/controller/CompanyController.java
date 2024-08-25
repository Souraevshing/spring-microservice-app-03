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

    @GetMapping("{id}")
    public ResponseEntity<Company> findById(@PathVariable Long id) {
        return companyService.findById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        return companyService.updateCompany(id, company);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        return companyService.deleteCompany(id);
    }

}

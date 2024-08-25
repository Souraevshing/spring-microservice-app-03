package com.demo.company_microservice.company.service;

import com.demo.company_microservice.company.model.Company;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompanyService {

    ResponseEntity<Company> createCompany (Company company);

    ResponseEntity<List<Company>> findAllJobs();

    ResponseEntity<Company> findById(Long companyId);

    ResponseEntity<Company> updateCompany(Long companyId, Company company);

    ResponseEntity<String> deleteCompany(Long companyId);

}

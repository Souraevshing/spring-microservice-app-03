package com.demo.company_microservice.company.service.impl;

import com.demo.company_microservice.company.model.Company;
import com.demo.company_microservice.company.repository.CompanyRepository;
import com.demo.company_microservice.company.service.CompanyService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Override
    public ResponseEntity<Company> createCompany(Company company) {
        LOGGER.info("Creating company");

        try {
            companyRepository.save(company);
            LOGGER.info("Company created successfully");
            return new ResponseEntity<>(company, HttpStatus.CREATED);
        } catch (Exception exception) {
            LOGGER.error("Failed to create company {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Company>> findAllJobs() {
        LOGGER.info("Fetching all companies");

        try {
            LOGGER.info("Fetched all companies");
            return new ResponseEntity<>(companyRepository.findAll(), HttpStatus.OK);
        } catch (Exception exception) {
            LOGGER.error("Failed fetching all companies {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Company> findById(Long companyId) {
        LOGGER.info("Fetching company with id : {}", companyId);

        try {
            Optional<Company> job = companyRepository
                    .findById(companyId)
                    .stream()
                    .filter(j -> j.getId().equals(companyId))
                    .findFirst();

            return job.map(j -> {
                LOGGER.info("Company found with id: {}", companyId);
                return new ResponseEntity<>(j, HttpStatus.OK);
            }).orElseGet(() -> {
                LOGGER.warn("Company not found with id: {}", companyId);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            });
        } catch(Exception exception) {
            LOGGER.error("Failed to fetch company {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Company> updateCompany(Long companyId, Company updatedCompany) {
        LOGGER.info("Updating company with id: {}", companyId);

        try {
            Optional<Company> existingCompany = companyRepository.findById(companyId);

            if (existingCompany.isPresent()) {
                Company c = existingCompany.get();
                c.setName(updatedCompany.getName());
                c.setDescription(updatedCompany.getDescription());
                companyRepository.save(c);

                LOGGER.info("Updated company with id: {}", companyId);

                return new ResponseEntity<>(c, HttpStatus.OK);
            } else {
                LOGGER.error("Company does not exist with id: {}", companyId);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            LOGGER.error("Failed to update company {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> deleteCompany(Long companyId) {
        LOGGER.info("Deleting company");

        try {
            boolean existingCompany = companyRepository.existsById(companyId);
            if (existingCompany) {
                companyRepository.deleteById(companyId);
                LOGGER.info("Company deleted with id {}", companyId);
                return new ResponseEntity<>("DELETED", HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            LOGGER.error("Failed to delete company {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

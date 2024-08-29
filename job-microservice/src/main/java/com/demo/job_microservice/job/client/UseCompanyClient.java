package com.demo.job_microservice.job.client;

import com.demo.job_microservice.job.model.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Implemented FeignClient for inter-communication between multiple services
 * */
@FeignClient(name = "COMPANY-MICROSERVICE")
public interface UseCompanyClient {

    @GetMapping("/api/v1/companies/{companyId}")
    Company findByCompanyId(@PathVariable Long companyId);

}

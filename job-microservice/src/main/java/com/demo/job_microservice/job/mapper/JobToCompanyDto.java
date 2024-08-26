package com.demo.job_microservice.job.mapper;

import com.demo.job_microservice.job.dto.JobDto;
import com.demo.job_microservice.job.model.Company;
import com.demo.job_microservice.job.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class JobToCompanyDto {

    @Autowired
    WebClient.Builder webClient;

    public JobDto convertToDto(Job job) {

        JobDto jobDto = new JobDto();
        jobDto.setJob(job);

        Company company = webClient
                .build()
                .get()
                .uri("http://COMPANY-MICROSERVICE:8082/api/v1/companies/"
                        + job.getCompanyId())
                .retrieve()
                .bodyToMono(Company.class)
                .block();

        jobDto.setCompany(company);

        return jobDto;
    }

}

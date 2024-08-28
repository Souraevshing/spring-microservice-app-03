package com.demo.job_microservice.job.mapper;

import com.demo.job_microservice.job.dto.JobDto;
import com.demo.job_microservice.job.model.Company;
import com.demo.job_microservice.job.model.Job;
import com.demo.job_microservice.job.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Configuration
public class JobToCompanyDto {

    @Autowired
    WebClient.Builder webClient;

    public JobDto convertToDto(Job job) {
        Company company = webClient
                .build()
                .get()
                .uri("http://COMPANY-MICROSERVICE:8082/api/v1/companies/"
                        + job.getCompanyId()
                )
                .retrieve()
                .bodyToMono(Company.class)
                .block();

        List<Review> review = webClient
                .build()
                .get()
                .uri("http://REVIEW-MICROSERVICE:8083/api/v1/reviews?companyId="
                        + job.getCompanyId()
                )
                .retrieve()
                .bodyToFlux(Review.class)
                .collectSortedList()
                .block();

        JobDto jobDto = JobMapper.mapToJobDto(job, company, review);
        jobDto.setCompany(company);

        return jobDto;
    }

}

package com.demo.job_microservice.job.dto;

import com.demo.job_microservice.job.client.UseCompanyClient;
import com.demo.job_microservice.job.client.UseReviewClient;
import com.demo.job_microservice.job.mapper.JobMapper;
import com.demo.job_microservice.job.model.Company;
import com.demo.job_microservice.job.model.Job;
import com.demo.job_microservice.job.model.Review;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Using FeignClient for inter-communication between multiple services.
 *
 * The same code for WebClient is commented below.
 * */

@Configuration
@AllArgsConstructor
public class JobToCompanyDto {

    private UseCompanyClient useCompanyClient;
    private UseReviewClient useReviewClient;

    public JobDto convertToDto(Job job) {

        // calling FeignClient implementation of UseCompanyClient to fetch the company by id
        Company company = useCompanyClient
                .findByCompanyId(job.getCompanyId());

        // calling FeignClient implementation of ReviewClient to fetch reviews for the company by id
        List<Review> review = useReviewClient
                .findReviewByCompanyId(job.getCompanyId());

//        Company company = webClient
//                .build()
//                .get()
//                .uri("http://COMPANY-MICROSERVICE:8082/api/v1/companies/"
//                        + job.getCompanyId()
//                )
//                .retrieve()
//                .bodyToMono(Company.class)
//                .block();

//        List<Review> review = webClient
//                .build()
//                .get()
//                .uri("http://REVIEW-MICROSERVICE:8083/api/v1/reviews?companyId="
//                        + job.getCompanyId()
//                )
//                .retrieve()
//                .bodyToFlux(Review.class)
//                .collectSortedList()
//                .block();

        JobDto jobDto = JobMapper.mapToJobDto(job, company, review);
        jobDto.setCompany(company);

        return jobDto;
    }

}

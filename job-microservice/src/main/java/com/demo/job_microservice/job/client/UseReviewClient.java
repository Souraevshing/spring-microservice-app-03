package com.demo.job_microservice.job.client;

import com.demo.job_microservice.job.model.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Implemented FeignClient for inter-communication between multiple services
 * */
@FeignClient(name = "REVIEW-MICROSERVICE")
public interface UseReviewClient {

    @GetMapping("/api/v1/reviews")
    List<Review> findReviewByCompanyId(@RequestParam("companyId") Long companyId);

}

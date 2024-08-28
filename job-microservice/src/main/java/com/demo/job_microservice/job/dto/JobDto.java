package com.demo.job_microservice.job.dto;

import com.demo.job_microservice.job.model.Company;
import com.demo.job_microservice.job.model.Review;
import lombok.Data;

import java.util.List;

@Data
public class JobDto {

    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Company company;
    private List<Review> reviews;

}

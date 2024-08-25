package com.demo.job_microservice.job.dto;

import com.demo.job_microservice.job.model.Company;
import com.demo.job_microservice.job.model.Job;
import lombok.Data;

@Data
public class JobDto {

    private Job job;
    private Company company;

}

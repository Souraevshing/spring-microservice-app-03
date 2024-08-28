package com.demo.job_microservice.job.mapper;

import com.demo.job_microservice.job.dto.JobDto;
import com.demo.job_microservice.job.model.Company;
import com.demo.job_microservice.job.model.Job;
import com.demo.job_microservice.job.model.Review;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class JobMapper {

    public static JobDto mapToJobDto(Job job,
                                     Company company,
                                     List<Review> review) {
        JobDto jobDto = new JobDto();
        jobDto.setId(job.getId());
        jobDto.setTitle(job.getTitle());
        jobDto.setDescription(job.getDescription());
        jobDto.setLocation(job.getLocation());
        jobDto.setMinSalary(job.getMinSalary());
        jobDto.setMaxSalary(job.getMaxSalary());
        jobDto.setCompany(company);
        jobDto.setReviews(review);

        return jobDto;
    }

}

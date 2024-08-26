package com.demo.job_microservice.job.service;

import com.demo.job_microservice.job.dto.JobDto;
import com.demo.job_microservice.job.model.Job;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JobService {

    ResponseEntity<List<JobDto>> findAllJobs();

    ResponseEntity<Job> createJob(Job job);

    ResponseEntity<JobDto> findJobById(Long companyId);

    ResponseEntity<String> deleteJobById(Long companyId);

    ResponseEntity<Job> updateJob(Long companyId, Job job);
}

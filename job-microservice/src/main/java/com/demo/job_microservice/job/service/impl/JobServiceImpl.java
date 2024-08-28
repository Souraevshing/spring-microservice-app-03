package com.demo.job_microservice.job.service.impl;

import com.demo.job_microservice.job.dto.JobDto;
import com.demo.job_microservice.job.mapper.JobToCompanyDto;
import com.demo.job_microservice.job.service.JobService;
import com.demo.job_microservice.job.model.Job;
import com.demo.job_microservice.job.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;
    private JobToCompanyDto jobToCompanyDto;
    private static final Logger LOGGER = LoggerFactory.getLogger(JobServiceImpl.class);

    @Override
    public ResponseEntity<List<JobDto>> findAllJobs() {
        LOGGER.info("Fetching all jobs");

        try {
            LOGGER.info("Fetched all jobs");

            List<Job> jobs = jobRepository.findAll();
            List<JobDto> jobDtos = jobs
                    .stream()
                    .map(job -> jobToCompanyDto.convertToDto(job))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(jobDtos, HttpStatus.OK);
        } catch(Exception exception) {
            LOGGER.error("Failed to fetch all jobs {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<JobDto> findJobById(Long companyId) {
        LOGGER.info("Fetching job with id : {}", companyId);

        try {
            Job job = jobRepository.findById(companyId).orElseThrow();
            LOGGER.info("Job fetched successfully with id {}", companyId);
            JobDto jobDto = jobToCompanyDto.convertToDto(job);

            return new ResponseEntity<>(jobDto, HttpStatus.OK);
        } catch(Exception exception) {
            LOGGER.error("Failed to fetch job {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Job> createJob(Job job) {
        LOGGER.info("Creating job");

        try {
            jobRepository.save(job);
            LOGGER.info("Job created successfully");
            return new ResponseEntity<>(job, HttpStatus.CREATED);
        } catch (Exception exception) {
            LOGGER.error("Failed to create job {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> deleteJobById(Long companyId) {
        LOGGER.info("Deleting job with id: {}", companyId);

        try {
            boolean isJobExists = findJobById(companyId).hasBody();
            if (isJobExists) {
                jobRepository.deleteById(companyId);
                LOGGER.info("Job deleted with id: {}", companyId);
                return new ResponseEntity<>("DELETED", HttpStatus.OK);
            }

            LOGGER.info("Job does not exist with id: {}", companyId);
            return new ResponseEntity<>("Job do not exist", HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            LOGGER.error("Failed to delete job {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Job> updateJob(Long companyId, Job updatedJob) {
        LOGGER.info("Updating job with id: {}", companyId);

        try {
            Optional<Job> existingJob = jobRepository.findById(companyId);

            if (existingJob.isPresent()) {
                Job job = existingJob.get();
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setLocation(updatedJob.getLocation());
                jobRepository.save(job);

                LOGGER.info("Updated job with id: {}", companyId);

                return new ResponseEntity<>(job, HttpStatus.OK);
            } else {
                LOGGER.error("Job does not exist with id: {}", companyId);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            LOGGER.error("Failed to update job {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

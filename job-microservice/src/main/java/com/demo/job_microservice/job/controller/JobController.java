package com.demo.job_microservice.job.controller;

import com.demo.job_microservice.job.dto.JobDto;
import com.demo.job_microservice.job.model.Job;
import com.demo.job_microservice.job.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@Validated
@RequestMapping("/api/v1/jobs")
public class JobController {

    private JobService jobService;

    @GetMapping
    public ResponseEntity<List<JobDto>> findAllJobs() {
        return jobService.findAllJobs();
    }

    @GetMapping("{jobId}")
    public ResponseEntity<JobDto> findJobById(@PathVariable Long jobId) {
        return jobService.findJobById(jobId);
    }

    @PostMapping()
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }

    @DeleteMapping("{jobId}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long jobId) {
        return jobService.deleteJobById(jobId);
    }

    @PutMapping("{jobId}")
    public ResponseEntity<Job> updateJob(@PathVariable Long jobId, @RequestBody Job job) {
        return jobService.updateJob(jobId, job);
    }

}

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

    @GetMapping("{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    @PostMapping()
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        return jobService.deleteJobById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job job) {
        return jobService.updateJob(id, job);
    }

}

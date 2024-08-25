package com.demo.job_microservice.job.repository;

import com.demo.job_microservice.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {
    Optional<Job> findByTitle(String name);
}

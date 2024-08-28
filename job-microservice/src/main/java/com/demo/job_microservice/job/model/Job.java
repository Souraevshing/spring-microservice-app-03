package com.demo.job_microservice.job.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "job_tab")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "Title is required")
    @Column(length = 200)
    private String title;

    @NotNull
    @NotBlank(message = "Description is required")
    @Column(length = 500)
    private String description;

    @NotNull
    @NotBlank(message = "Minimum salary is required")
    @Column(length = 50)
    private String minSalary;

    @NotNull
    @NotBlank(message = "Maximum salary is required")
    @Column(length = 50)
    private String maxSalary;

    @NotNull
    @NotBlank(message = "Location is required")
    @Column(length = 100)
    private String location;

    private Long companyId;

}

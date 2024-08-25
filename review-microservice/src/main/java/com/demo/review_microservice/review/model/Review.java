package com.demo.review_microservice.review.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "review_tab")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "Title is required")
    @Column(length = 100)
    private String title;

    @NotNull
    @NotBlank(message = "Description is required")
    @Column(length = 200)
    private String description;
    
    private double rating;

    private Long companyId;

}

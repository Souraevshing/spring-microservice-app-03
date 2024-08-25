package com.demo.review_microservice.review.service;

import com.demo.review_microservice.review.model.Review;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService {

    ResponseEntity<List<Review>> findAllReviews(Long companyId);

    ResponseEntity<Review> createReview(Long companyId, Review review);

    ResponseEntity<Review> findReviews(Long reviewId);

    ResponseEntity<Review> updateReview(Long reviewId, Review updatedReview);

    ResponseEntity<String> deleteReview(Long reviewId);

}

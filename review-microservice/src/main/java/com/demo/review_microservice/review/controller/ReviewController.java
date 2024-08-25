package com.demo.review_microservice.review.controller;

import com.demo.review_microservice.review.model.Review;
import com.demo.review_microservice.review.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@Validated
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> findAllReviews(@RequestParam Long companyId) {
        return reviewService.findAllReviews(companyId);
    }

    @GetMapping("{reviewId}")
    public ResponseEntity<Review> findReviews(@PathVariable Long reviewId) {
        return reviewService.findReviews(reviewId);
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestParam Long companyId,
                                               @RequestBody Review review) {
        return reviewService.createReview(companyId, review);
    }

    @PutMapping("{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long reviewId,
                                               @RequestBody Review review) {
        return reviewService.updateReview(reviewId, review);
    }

    @DeleteMapping("{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        return reviewService.deleteReview(reviewId);
    }

}

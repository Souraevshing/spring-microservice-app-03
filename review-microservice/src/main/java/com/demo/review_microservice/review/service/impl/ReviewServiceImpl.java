package com.demo.review_microservice.review.service.impl;

import com.demo.review_microservice.review.model.Review;
import com.demo.review_microservice.review.repository.ReviewRepository;
import com.demo.review_microservice.review.service.ReviewService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewServiceImpl.class);

    @Override
    public ResponseEntity<List<Review>> findAllReviews(Long companyId) {
        LOGGER.info("Fetching all reviews");

        try {
            List<Review> reviews = reviewRepository.findByCompanyId(companyId);
            LOGGER.info("Fetched all reviews");
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception exception) {
            LOGGER.error("Failed fetching reviews {}", exception.getMessage());
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Review> createReview(Long companyId, Review review) {
        LOGGER.info("Creating review");

        try {
            if (companyId != null && review != null) {
                review.setCompanyId(companyId);
                reviewRepository.save(review);

                LOGGER.info("Review created successfully");

                return new ResponseEntity<>(review, HttpStatus.CREATED);
            } else {
                LOGGER.error("Failed to create review");
                return new ResponseEntity<>(review, HttpStatus.CONFLICT);
            }
        } catch (Exception exception) {
            LOGGER.error("Failed to create review {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Review> findReviews(Long reviewId) {
        LOGGER.info(" Fetching all reviews by company id");

        try {
            LOGGER.info("Fetched all reviews for company id");

            return new ResponseEntity<>(reviewRepository.findById(reviewId).orElse(null), HttpStatus.OK);
        } catch (Exception exception) {
            LOGGER.error("Failed to fetch reviews for company id {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Review> updateReview(Long reviewId, Review updatedReview) {
        LOGGER.info("Updating review with id {}", reviewId);

        try {
            Optional<Review> existingReviewOpt = reviewRepository.findById(reviewId);

            if (existingReviewOpt.isPresent()) {
                Review review = existingReviewOpt.get();

                review.setTitle(updatedReview.getTitle());
                review.setDescription(updatedReview.getDescription());
                review.setRating(updatedReview.getRating());
                review.setCompanyId(updatedReview.getCompanyId());

                reviewRepository.save(review);

                LOGGER.info("Review updated successfully with id {}", reviewId);

                return new ResponseEntity<>(review, HttpStatus.OK);
            } else {
                LOGGER.error("Review with id {} not found", reviewId);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            LOGGER.error("Failed to update review with id {}: {}", reviewId, exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> deleteReview(Long reviewId) {
        LOGGER.info("Deleting review with id {}", reviewId);

        try {
            Optional<Review> reviewOpt = reviewRepository.findById(reviewId);

            if (reviewOpt.isPresent()) {
                reviewRepository.delete(reviewOpt.get());

                LOGGER.info("Review deleted with id {}", reviewId);
                return new ResponseEntity<>("DELETED", HttpStatus.OK);
            } else {
                LOGGER.error("Review with id {} not found", reviewId);
                return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            LOGGER.error("Failed to delete review with id {}: {}", reviewId, exception.getMessage());
            return new ResponseEntity<>("Failed to delete review", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

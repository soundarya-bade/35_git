package com.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.entities.Review;
import com.restapi.service.ReviewService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
		
	@Autowired
	ReviewService reviewService;
	
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public void createReview(@RequestBody Review review) {
		log.info("Review Controller:: createReview {}",review.getId());
		reviewService.createReview(review);
		
	}
	
	@GetMapping("{reviewId}")
	public Review getReviewById(@PathVariable Long reviewId) {
		log.info("Review Controller:: getReviewById {}",reviewId);
		return reviewService.fetchReviewById(reviewId);
		
	}
	
	@PutMapping("{reviewId}")
	public void updateReview(@PathVariable Long reviewId,@RequestBody Review review) {
		log.info("Review Controller:: updateReview {}",review.getId());
		reviewService.updateReview(reviewId, review);
		
	}
	
	@DeleteMapping("{reviewId}")
	public void deleteReview(@PathVariable Long reviewId) {
		reviewService.deleteReview(reviewId);
		
	}
}

package com.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.entities.Review;
import com.restapi.exception.ResourceNotFoundException;
import com.restapi.repository.ReviewRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReviewService {

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	ProductService productService;

	public void createReview(Review review) {
		log.info("Review Service:: createReview {}",review.getId());
		reviewRepository.save(review);
		log.info("Review saved successfully");
	}

	public Review fetchReviewById(Long reviewId) {
		return reviewRepository.findById(reviewId).orElseThrow(()-> new ResourceNotFoundException("Review not found"));
	}

	public void updateReview(Long reviewId, Review inputReview) {
		Review dbReview=fetchReviewById(reviewId);
		if(null!=productService.fetchProductById(inputReview.getProduct().getId())){
			log.info("Review Service:: updateReview {}",dbReview.getId());
			dbReview.setComment(inputReview.getComment());
			reviewRepository.save(dbReview);
			log.info("Review updated successfully");		
		}
		else {
			throw new ResourceNotFoundException("Product not found");
		}
		
	}
	public void deleteReview(Long reviewId) {

		if(reviewRepository.existsById(reviewId)) {
			log.info("Review Service:: deleteProduct {}",reviewId);
			reviewRepository.deleteById(reviewId);
			log.info("Review deleted successfully");
		}
		else {
			throw new ResourceNotFoundException("Review not found");
		}
	}

}

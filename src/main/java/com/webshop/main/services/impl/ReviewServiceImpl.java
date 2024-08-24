package com.webshop.main.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webshop.main.dtos.ReviewDto;
import com.webshop.main.models.Product;
import com.webshop.main.models.Review;
import com.webshop.main.repositories.ReviewRepository;
import com.webshop.main.services.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{
	private ReviewRepository reviewRepository;
	
	public ReviewServiceImpl(ReviewRepository reviewRepository) {
		super();
		this.reviewRepository = reviewRepository;
	}

	@Override
	public Review mapToReview(ReviewDto reviewDto) {
		return Review.builder().id(reviewDto.getId()).rating(reviewDto.getRating()).comment(reviewDto.getComment()).build();
	}

	@Override
	public ReviewDto mapToReviewDto(Review review) {
		return ReviewDto.builder().id(review.getId()).rating(review.getRating()).comment(review.getComment()).build();
	}

	@Override
	public void createReview(Review review) {
		reviewRepository.save(review);
	}

	@Override
	public List<Review> findReviewsByProduct(Product product) {
		return reviewRepository.findReviewsByProduct(product);
	}

}
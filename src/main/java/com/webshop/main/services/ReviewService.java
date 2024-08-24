package com.webshop.main.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webshop.main.dtos.ReviewDto;
import com.webshop.main.models.Product;
import com.webshop.main.models.Review;

@Service
public interface ReviewService {
	Review mapToReview(ReviewDto reviewDto);
	
	ReviewDto mapToReviewDto(Review review);
	
	void createReview(Review review);
	
	List<Review> findReviewsByProduct(Product product);
}

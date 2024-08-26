package com.webshop.main.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.webshop.main.dtos.ReviewDto;
import com.webshop.main.models.Product;
import com.webshop.main.models.Review;
import com.webshop.main.models.UserEntity;
import com.webshop.main.services.ProductService;
import com.webshop.main.services.ReviewService;
import com.webshop.main.services.UserService;

import jakarta.validation.Valid;

@Controller
public class ReviewController {
	private ProductService productService;
	private ReviewService reviewService;
	private UserService userService;
	
	public ReviewController(ProductService productService, ReviewService reviewService, UserService userService) {
		super();
		this.productService = productService;
		this.reviewService = reviewService;
		this.userService = userService;
	}

	@PostMapping("/products/{productId}/newReview")
	public String postNewReview(@PathVariable("productId") Long productId, @Valid @ModelAttribute("review") ReviewDto reviewDto, BindingResult result, Principal principal) {
		if (result.hasErrors()) {
			return "redirect:/products/" + productId + "?invalidReview";
		}
		
		Product product = productService.findProductById(productId);
		Review review = reviewService.mapToReview(reviewDto);
		UserEntity reviewer = userService.findByEmail(principal.getName());
		review.setProduct(product);
		review.setReviewer(reviewer);
		
		
		reviewService.createReview(review);
		
		product.getProductReviews().add(review);
		reviewer.getMyReviews().add(review);
		
		double ratingSum = 0.0;
		int revNum = 0;
		double rating = 0.0;
		for (Review rev : product.getProductReviews()) {
			ratingSum += rev.getRating();
			revNum += 1;
		}
		
		
		rating = ratingSum / revNum;
		product.setRating(rating);
		productService.updateProduct(product);
		
		return "redirect:/products/" + product.getId() + "?successRev";
	}
	
	@GetMapping("/products/{productId}/reviews")
	public String getProductReviews(@PathVariable("productId") Long productId, Model model) {
		Product product = productService.findProductById(productId);
		List<Review> reviews = reviewService.findReviewsByProduct(product);
		model.addAttribute("reviews", reviews);
		model.addAttribute("product", product);
		return "reviews";
	}
}

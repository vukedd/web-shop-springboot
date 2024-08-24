package com.webshop.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.main.models.Product;
import com.webshop.main.models.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findReviewsByProduct(Product product);
}

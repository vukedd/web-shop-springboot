package com.webshop.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.main.models.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	
}

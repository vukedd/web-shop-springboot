package com.webshop.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.main.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
}

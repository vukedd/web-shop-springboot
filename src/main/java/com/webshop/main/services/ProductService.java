package com.webshop.main.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webshop.main.models.Product;

@Service
public interface ProductService {
	List<Product> findAllProducts();
	Product findProductById(Long id);
}

package com.webshop.main.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webshop.main.models.Product;
import com.webshop.main.repositories.ProductRepository;
import com.webshop.main.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	private ProductRepository productRepo;
	
	

	public ProductServiceImpl(ProductRepository productRepo) {
		super();
		this.productRepo = productRepo;
	}



	@Override
	public List<Product> findAllProducts() {
		List<Product> products = productRepo.findAll();
		return products;
	}



	@Override
	public Product findProductById(Long id) {
		Product product = productRepo.findById(id).get();
		return product;
	}

}

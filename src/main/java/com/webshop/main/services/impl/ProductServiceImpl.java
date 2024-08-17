package com.webshop.main.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webshop.main.dtos.ProductDto;
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

	public ProductDto mapToProductDto(Product product) {
		ProductDto productDto = ProductDto.builder().id(product.getId()).name(product.getName()).photoUrl(product.getPhotoUrl()).description(product.getDescription()).price(product.getPrice()).category(product.getCategory()).stockQuantity(product.getStockQuantity()).build();
		return productDto;
	}
	
	public Product mapToProduct(ProductDto productDto) {
		Product product = Product.builder().id(productDto.getId()).name(productDto.getName()).photoUrl(productDto.getPhotoUrl()).description(productDto.getDescription()).price(productDto.getPrice()).category(productDto.getCategory()).stockQuantity(productDto.getStockQuantity()).build();
		return product;
	}

	@Override
	public Product createProduct(ProductDto newProduct) {
		Product product = mapToProduct(newProduct);
		return productRepo.save(product);
	}

	@Override
	public void updateProduct(Product product) {
		productRepo.save(product);
	}
}

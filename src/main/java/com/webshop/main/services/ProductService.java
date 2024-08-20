package com.webshop.main.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.webshop.main.dtos.ProductDto;
import com.webshop.main.models.Product;

@Service
public interface ProductService {
	List<Product> findAllProducts();
	
	Product findProductById(Long id);
	
	Product createProduct(ProductDto newProduct);
	
	Page<Product> findPaginated(int page, int size);
	
	void updateProduct(Product product);
	
	void deleteProduct(Product product);
}

package com.webshop.main.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.webshop.main.models.Product;
import com.webshop.main.services.ProductService;

@Controller
public class ProductController {
	
	private ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	@GetMapping("/")
	public String getAllProducts(Model model) {
		List<Product> products = productService.findAllProducts();
		model.addAttribute("products", products);
		return "index.html";
	}
	
	@GetMapping("products/{productId}")
	public String getProduct(@PathVariable("productId")Long productId ,Model model) {
		Product product = productService.findProductById(productId);
		List<Product> products = productService.findAllProducts();
		model.addAttribute("product", product);
		model.addAttribute("products", products);
		return "product-details";
	}
}

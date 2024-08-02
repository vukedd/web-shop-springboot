package com.webshop.main.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}

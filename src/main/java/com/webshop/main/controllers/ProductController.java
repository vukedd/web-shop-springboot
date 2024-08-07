package com.webshop.main.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webshop.main.models.CartItem;
import com.webshop.main.models.Product;
import com.webshop.main.services.ProductService;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class ProductController {
	
	private ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	@GetMapping("/")
	public String getAllProducts(Model model, HttpServletRequest request) {
		List<Product> products = productService.findAllProducts();
		model.addAttribute("products", products);
		return "index.html";
	}
	
	@GetMapping("/products/{productId}")
	public String getProduct(@PathVariable("productId")Long productId ,Model model) {
		Product product = productService.findProductById(productId);
		List<Product> products = productService.findAllProducts();
		CartItem cartItem = new CartItem();
		model.addAttribute("cartItem", cartItem);
		model.addAttribute("product", product);
		model.addAttribute("products", products);
		return "product-details";
	}
	
	@PostMapping("/products/{productId}/addToCart")
	public String addToCart(@Valid @ModelAttribute CartItem cartItem, @PathVariable("productId") int productId, BindingResult result, Model model) {
		model.addAttribute("cartItem", cartItem);
		System.out.println();
		return "redirect:/?success";
	}
}

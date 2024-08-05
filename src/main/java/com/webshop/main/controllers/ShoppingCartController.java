package com.webshop.main.controllers;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webshop.main.services.ProductService;
import com.webshop.main.services.ShoppingCartService;

//import com.webshop.main.models.ShoppingCart;
//import com.webshop.main.services.ShoppingCartService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ShoppingCartController {
	private ProductService productService;
	private ShoppingCartService cartService;
//
//	public ShoppingCartController(ShoppingCartService cartService) {
//		super();
//		this.cartService = cartService;
//	}
	
//	@PostMapping("/cart/add/{id}/addToCart")
//	public String addToCart(@PathVariable("id") Long ProductId, @RequestParam("quantity") int quantity, HttpSession session, Model model) {
//		
//		String sessionToken = (String) session.getAttribute("sessionToken");
//		if (sessionToken == null) {
//			sessionToken = UUID.randomUUID().toString();
//			session.setAttribute("sessionToken", sessionToken);
//			cartService.addShoppingCartFirstTime(ProductId, sessionToken, quantity);
//		}
//		else {
//			cartService.addToExistingShoppingCart(ProductId, sessionToken, quantity);
//		}
//		return "redirect:/products/{id}";
//	}
}

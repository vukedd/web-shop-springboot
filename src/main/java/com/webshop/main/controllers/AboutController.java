package com.webshop.main.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.webshop.main.models.ShoppingCart;
import com.webshop.main.models.UserEntity;
import com.webshop.main.services.ShoppingCartService;
import com.webshop.main.services.UserService;

@Controller
public class AboutController {
	private UserService userService;
	private ShoppingCartService cartService;
	
	public AboutController(UserService userService, ShoppingCartService cartService) {
		super();
		this.userService = userService;
		this.cartService = cartService;
	}

	@GetMapping("/about")
	public String getAboutUs(Model model, Principal principal) {
		if (principal != null) {
			UserEntity user = userService.findByEmail(principal.getName());
			ShoppingCart cart = cartService.findShoppingCartByUserId(user);
			model.addAttribute("cart", cart);
		} else {
			ShoppingCart cart = null;
			model.addAttribute("cart", cart);
		}
		return "about-us";
	}
}

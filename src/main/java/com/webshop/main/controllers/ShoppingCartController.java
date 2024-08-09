package com.webshop.main.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.webshop.main.models.ShoppingCart;
import com.webshop.main.models.UserEntity;
import com.webshop.main.services.ShoppingCartService;
import com.webshop.main.services.UserService;

//import com.webshop.main.models.ShoppingCart;
//import com.webshop.main.services.ShoppingCartService;


@Controller
public class ShoppingCartController {
	private UserService userService;
	private ShoppingCartService cartService;
	
	public ShoppingCartController(UserService userService, ShoppingCartService cartService) {
		super();
		this.userService = userService;
		this.cartService = cartService;
	}

	@GetMapping("/cart")
	public String getShoppingCart(Model model, Principal principal) {
		UserEntity user = userService.findByEmail(principal.getName());
		ShoppingCart cart = cartService.findShoppingCartByUserId(user);
		model.addAttribute("cart", cart);
		return "shopping-cart";
	}
	
}

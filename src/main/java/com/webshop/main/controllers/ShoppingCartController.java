package com.webshop.main.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.webshop.main.models.CartItem;
import com.webshop.main.models.Product;
import com.webshop.main.models.ShoppingCart;
import com.webshop.main.models.UserEntity;
import com.webshop.main.services.ProductService;
import com.webshop.main.services.ShoppingCartService;
import com.webshop.main.services.UserService;

//import com.webshop.main.models.ShoppingCart;
//import com.webshop.main.services.ShoppingCartService;


@Controller
public class ShoppingCartController {
	private UserService userService;
	private ShoppingCartService cartService;
	private ProductService productService;
	
	public ShoppingCartController(UserService userService, ShoppingCartService cartService, ProductService productService) {
		super();
		this.userService = userService;
		this.cartService = cartService;
		this.productService = productService;
	}

	@GetMapping("/cart")
	public String getShoppingCart(Model model, Principal principal) {
		UserEntity user = userService.findByEmail(principal.getName());
		ShoppingCart cart = cartService.findShoppingCartByUserId(user);
		List<Product> products = productService.findAllProducts();
		CartItem cartItem = new CartItem();
		model.addAttribute("cartItem", cartItem);
		model.addAttribute("products", products);
		model.addAttribute("cart", cart);
		return "shopping-cart";
	}
	
}

package com.webshop.main.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.webshop.main.models.CartItem;
import com.webshop.main.models.ShoppingCart;
import com.webshop.main.models.UserEntity;
import com.webshop.main.services.CartItemService;
import com.webshop.main.services.ShoppingCartService;
import com.webshop.main.services.UserService;

@Controller
public class CartItemController {
	private CartItemService cartItemService;
	private ShoppingCartService cartService;
	private UserService userService;
	
	public CartItemController(CartItemService cartItemService, ShoppingCartService cartService, UserService userService) {
		super();
		this.cartItemService = cartItemService;
		this.cartService = cartService;
		this.userService = userService;
	}

	@GetMapping("/cart/{cartItemId}/delete")
	public String RemoveCartItem(@PathVariable("cartItemId")Long cartItemId, Principal principal) {
		CartItem cartItem = cartItemService.findCartItemById(cartItemId);
		ShoppingCart cart = cartItem.getShoppingCart();
		cartItemService.removeCartItem(cartItem);
		return "redirect:/cart?delItem";
	}
}

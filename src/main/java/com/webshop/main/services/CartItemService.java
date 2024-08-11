package com.webshop.main.services;

import org.springframework.stereotype.Service;

import com.webshop.main.models.CartItem;

@Service
public interface CartItemService {
	CartItem findCartItemById(Long id);
	
	void removeCartItem(CartItem cartItem);
}

package com.webshop.main.services;

import org.springframework.stereotype.Service;

import com.webshop.main.models.ShoppingCart;

@Service
public interface ShoppingCartService {
	ShoppingCart addShoppingCartFirstTime(Long id, String sessionToken, int quantity);

	ShoppingCart addToExistingShoppingCart(Long productId, String sessionToken, int quantity);

	void save(ShoppingCart cart);
}

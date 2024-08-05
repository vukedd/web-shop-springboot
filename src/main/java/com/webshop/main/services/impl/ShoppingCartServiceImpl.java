package com.webshop.main.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.webshop.main.models.CartItem;
import com.webshop.main.models.Product;
import com.webshop.main.models.ShoppingCart;
import com.webshop.main.repositories.ShoppingCartRepository;
import com.webshop.main.services.ProductService;
import com.webshop.main.services.ShoppingCartService;

public class ShoppingCartServiceImpl implements ShoppingCartService{
	@Autowired
	ProductService productService;
	ShoppingCartRepository cartRepo;

	@Override
	public ShoppingCart addShoppingCartFirstTime(Long productId, String sessionToken, int quantity) {
		ShoppingCart cart = new ShoppingCart();
		CartItem item = new CartItem();
		Product p = productService.findProductById(productId);
		item.setQuantity(quantity);
		item.setPrice(quantity * p.getPrice());
		item.setProductId(productId);
		item.setPhotoUrl(p.getPhotoUrl());
		cart.addItem(item);
		cart.setSessionToken(sessionToken);
		return cartRepo.save(cart);
	}

	@Override
	public ShoppingCart addToExistingShoppingCart(Long productId, String sessionToken, int quantity) {
		ShoppingCart cart = cartRepo.findBySessionToken(sessionToken);
		CartItem item = new CartItem();
		Product p = productService.findProductById(productId);
		item.setQuantity(quantity);
		item.setProductId(productId);
		item.setPhotoUrl(p.getPhotoUrl());
		cart.addItem(item);
		return cartRepo.save(cart);
	}
}

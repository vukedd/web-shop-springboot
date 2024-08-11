package com.webshop.main.services.impl;

import org.springframework.stereotype.Service;
import com.webshop.main.models.CartItem;
import com.webshop.main.models.ShoppingCart;
import com.webshop.main.repositories.CartItemRepository;
import com.webshop.main.services.CartItemService;

import jakarta.transaction.Transactional;

@Service
public class CartItemServiceImpl implements CartItemService {
	private CartItemRepository cartItemRepo;
	
	public CartItemServiceImpl(CartItemRepository cartItemRepo) {
		super();
		this.cartItemRepo = cartItemRepo;
	}

	@Override
	public CartItem findCartItemById(Long id) {
		CartItem cartItem =  cartItemRepo.findById(id).get();
		return cartItem;
	}

	@Override
	@Transactional
	public void removeCartItem(CartItem cartItem) {
		ShoppingCart cart = cartItem.getShoppingCart();
		cart.removeItem(cartItem);
		cartItemRepo.deleteCartItemById(cartItem.getId());
	}

}

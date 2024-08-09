package com.webshop.main.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webshop.main.models.ShoppingCart;
import com.webshop.main.models.UserEntity;
import com.webshop.main.repositories.ShoppingCartRepository;
import com.webshop.main.services.ProductService;
import com.webshop.main.services.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
	@Autowired
	ProductService productService;
	ShoppingCartRepository cartRepo;
	
	public ShoppingCartServiceImpl(ProductService productService, ShoppingCartRepository cartRepo) {
		super();
		this.productService = productService;
		this.cartRepo = cartRepo;
	}
	
	@Override
	public ShoppingCart addShoppingCartFirstTime(Long id, String sessionToken, int quantity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ShoppingCart addToExistingShoppingCart(Long productId, String sessionToken, int quantity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void save(ShoppingCart cart) {
		cartRepo.save(cart);
	}

	@Override
	public ShoppingCart findShoppingCartByUserId(UserEntity user) {
		ShoppingCart userCart = cartRepo.findShoppingCartByUser(user);
		return userCart;
	}

}

package com.webshop.main.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webshop.main.models.CartItem;
import com.webshop.main.models.Product;
import com.webshop.main.models.ShoppingCart;
import com.webshop.main.models.UserEntity;
import com.webshop.main.repositories.CartItemRepository;
import com.webshop.main.repositories.ShoppingCartRepository;
import com.webshop.main.services.CartItemService;
import com.webshop.main.services.ProductService;
import com.webshop.main.services.ShoppingCartService;

import jakarta.validation.Valid;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
	@Autowired
	ProductService productService;
	ShoppingCartRepository cartRepo;
	CartItemService cartItemService;
	CartItemRepository cartItemRepository;
	
	public ShoppingCartServiceImpl(ProductService productService, ShoppingCartRepository cartRepo, CartItemService cartItemService) {
		super();
		this.productService = productService;
		this.cartRepo = cartRepo;
		this.cartItemService = cartItemService;
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

	@Override
	public ShoppingCart findShoppingCartById(Long cartId) {
		ShoppingCart cart = cartRepo.findById(cartId).get();
		return cart;
	}

	@Override
	public void addToShoppingCart(@Valid CartItem cartItem, Product product, UserEntity user) {
		cartItem.setPhotoUrl(product.getPhotoUrl());
		cartItem.setProductId(product.getId());
		cartItem.setPrice(product.getPrice() * cartItem.getQuantity());
		if (user != null && user.getShopCart() == null) {
			ShoppingCart shopCart = new ShoppingCart();
			user.setShopCart(shopCart);
			user.getShopCart().addItem(cartItem);
			cartItem.setShoppingCart(shopCart);
			cartItem.setProductName(product.getName());
			cartItem.setProductCategory(product.getCategory());
			this.save(shopCart);
		} else {
			user.getShopCart().addItem(cartItem);
			cartItem.setShoppingCart(user.getShopCart());
			cartItem.setProductName(product.getName());
			cartItem.setProductCategory(product.getCategory());
			this.save(user.getShopCart());
		}
	}

}

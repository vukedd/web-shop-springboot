package com.webshop.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.main.models.ShoppingCart;
import com.webshop.main.models.UserEntity;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>{
	ShoppingCart findShoppingCartByUser(UserEntity user);
}

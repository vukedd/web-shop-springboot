package com.webshop.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webshop.main.models.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	void deleteCartItemById(Long id);
	
}

package com.webshop.main.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "shoppingCarts")
public class ShoppingCart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double totalPrice;
	private int itemCount;
	
	@OneToOne(mappedBy = "shopCart")
	private UserEntity user;
	
    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartItem> cartItems = new ArrayList<>();
    
	public ShoppingCart() {
		super();
		this.totalPrice = 0.0;
		this.itemCount = 0;
	}
    
    public ShoppingCart addItem(CartItem item) {
    	for (CartItem cartItem : this.cartItems) {
    		if (cartItem.getProductId() == item.getProductId()) {
    			cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
    			cartItem.setPrice(cartItem.getPrice() + (item.getPrice() * item.getQuantity()));
    	    	this.cartItems.add(item);
    	    	this.totalPrice += item.getPrice() * item.getQuantity();
    	    	this.itemCount += item.getQuantity();
    			return this;
    		}
    	}
    	
    	this.cartItems.add(item);
    	this.totalPrice += item.getPrice() * item.getQuantity();
    	this.itemCount += item.getQuantity();
    	
    	return this;
    }
}

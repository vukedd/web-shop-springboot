package com.webshop.main.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double totalPrice;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
	private UserEntity user;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();
    
    @OneToOne
    private OrdererInfo orderer;
    
	public Order() {
		super();
		this.totalPrice = 0.0;
	}
    
    public void addOrderItem(CartItem item) {
    	OrderItem orderItem = new OrderItem();
    	orderItem.setOrder(this);
    	orderItem.setPhotoUrl(item.getPhotoUrl());
    	orderItem.setPrice(item.getPrice());
    	orderItem.setProductCategory(item.getProductCategory());
    	orderItem.setProductId(item.getProductId());
    	orderItem.setProductName(item.getProductName());
    	orderItem.setQuantity(item.getQuantity());
    	this.totalPrice += item.getPrice();
    	
    	this.orderItems.add(orderItem);
    	
    }

}

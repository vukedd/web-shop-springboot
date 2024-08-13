package com.webshop.main.services;

import org.springframework.stereotype.Service;

import com.webshop.main.models.Order;

@Service
public interface OrderService {	
	void save(Order order);
}

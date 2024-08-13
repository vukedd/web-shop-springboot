package com.webshop.main.services.impl;

import org.springframework.stereotype.Service;

import com.webshop.main.models.Order;
import com.webshop.main.repositories.OrderRepository;
import com.webshop.main.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	private OrderRepository orderRepo;
	
	public OrderServiceImpl(OrderRepository orderRepo) {
		super();
		this.orderRepo = orderRepo;
	}

	@Override
	public void save(Order order) {
		orderRepo.save(order);
	}
	
}

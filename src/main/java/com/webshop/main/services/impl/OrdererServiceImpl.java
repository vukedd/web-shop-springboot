package com.webshop.main.services.impl;

import org.springframework.stereotype.Service;

import com.webshop.main.models.OrdererInfo;
import com.webshop.main.repositories.OrdererInfoRepository;
import com.webshop.main.services.OrdererService;

@Service
public class OrdererServiceImpl implements OrdererService{
	private OrdererInfoRepository ordererRepo;
	
	public OrdererServiceImpl(OrdererInfoRepository ordererRepo) {
		super();
		this.ordererRepo = ordererRepo;
	}

	@Override
	public void save(OrdererInfo info) {
		ordererRepo.save(info);
	}
	
}

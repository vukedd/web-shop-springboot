package com.webshop.main.services;

import org.springframework.stereotype.Service;

import com.webshop.main.models.OrdererInfo;

@Service
public interface OrdererService {
	void save(OrdererInfo info);
}

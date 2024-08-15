package com.webshop.main.services;

import org.springframework.stereotype.Service;

import com.webshop.main.models.Order;

@Service
public interface EmailSenderService {
	void sendOrdererEmail(Order order);
	void sendSellerEmail(Order order);
}

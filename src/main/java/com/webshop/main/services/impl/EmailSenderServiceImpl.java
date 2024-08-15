package com.webshop.main.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.webshop.main.models.Order;
import com.webshop.main.models.OrderItem;
import com.webshop.main.services.EmailSenderService;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendOrdererEmail(Order order) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("vukedd.aternos@gmail.com");
		message.setTo(order.getOrderer().getEmail());
		StringBuffer sbItems = new StringBuffer();
		for (OrderItem oi : order.getOrderItems()) {
			sbItems.append(oi.getProductName() + "\n" + "Quantity: " + oi.getQuantity() + "\nPrice: " + oi.getPrice() + "0 RSD\n\n");
		}
		
		message.setText(
				"Greetings, " + order.getOrderer().getFirstAndLastName() + "\n\n"
				+ "You have sucessfully placed an order : \n\n" + sbItems.toString()
				+ "Total price: " + order.getTotalPrice() + "0 RSD\n\n"
				+ "*Shipping details* \n"
				+ "Name: " + order.getOrderer().getFirstAndLastName() + "\n"
				+ "Shipping Address : " + order.getOrderer().getShippingAddress() + "\n"
				+ "City: " + order.getOrderer().getCity() + "\n"
				+ "ZIP Code: " + order.getOrderer().getZIPCode() + "\n"
				+ "Phone number: " + order.getOrderer().getPhoneNumber() + "\n"
		);
		
		message.setSubject("Order Confirmation");
		
		mailSender.send(message);
		
		System.out.println("Confirmation Mail sent successfully!");
	}

	@Override
	public void sendSellerEmail(Order order) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("vukedd.aternos@gmail.com");
		message.setTo("vukedd.aternos@gmail.com");
		StringBuffer sbItems = new StringBuffer();
		for (OrderItem oi : order.getOrderItems()) {
			sbItems.append(oi.getProductName() + "\n" + "Quantity: " + oi.getQuantity() + "\nPrice: " + oi.getPrice() + "0 RSD\n\n");
		}
		
		message.setText(
				"Order: \n\n" + sbItems.toString()
				+ "Total price: " + order.getTotalPrice() + "0 RSD\n\n"
				+ "Shipping details: \n"
				+ "Name: " + order.getOrderer().getFirstAndLastName() + "\n"
				+ "Shipping Address : " + order.getOrderer().getShippingAddress() + "\n"
				+ "City: " + order.getOrderer().getCity() + "\n"
				+ "ZIP Code: " + order.getOrderer().getZIPCode() + "\n"
				+ "Phone number: " + order.getOrderer().getPhoneNumber() + "\n"
		);
		
		message.setSubject("New order has been placed");
		mailSender.send(message);
		
		System.out.println("Order Mail sent successfully!");
	}
}

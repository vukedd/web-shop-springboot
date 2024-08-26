package com.webshop.main.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.webshop.main.models.CartItem;
import com.webshop.main.models.Order;
import com.webshop.main.models.OrderItem;
import com.webshop.main.models.OrdererInfo;
import com.webshop.main.models.Product;
import com.webshop.main.models.ShoppingCart;
import com.webshop.main.models.UserEntity;
import com.webshop.main.services.CartItemService;
import com.webshop.main.services.EmailSenderService;
import com.webshop.main.services.OrderService;
import com.webshop.main.services.OrdererService;
import com.webshop.main.services.ProductService;
import com.webshop.main.services.ShoppingCartService;
import com.webshop.main.services.UserService;

import jakarta.validation.Valid;

//import com.webshop.main.models.ShoppingCart;
//import com.webshop.main.services.ShoppingCartService;


@Controller
public class ShoppingCartController {
	private UserService userService;
	private ShoppingCartService cartService;
	private ProductService productService;
	private OrderService orderService;
	private OrdererService ordererService;
	private EmailSenderService emailService;
	
	public ShoppingCartController(EmailSenderService emailService ,UserService userService, ShoppingCartService cartService, ProductService productService, OrderService orderService, OrdererService ordererService) {
		super();
		this.userService = userService;
		this.cartService = cartService;
		this.productService = productService;
		this.orderService = orderService;
		this.ordererService = ordererService;
		this.emailService = emailService;
	}

	@GetMapping("/cart")
	public String getShoppingCart(Model model, Principal principal) {
		UserEntity user = userService.findByEmail(principal.getName());
		ShoppingCart cart = cartService.findShoppingCartByUserId(user);
		List<Product> products = productService.findAllProducts();
		CartItem cartItem = new CartItem();
		OrdererInfo orderer = new OrdererInfo();
		model.addAttribute("cartItem", cartItem);
		model.addAttribute("products", products);
		if (cart == null) {
			ShoppingCart newCart = new ShoppingCart();
			model.addAttribute("cart", newCart);
		} else {
			model.addAttribute("cart", cart);
		}
		model.addAttribute("orderer", orderer);
		return "shopping-cart";
	}
	
	@PostMapping("/cart/{cartId}/order")
	public String placeOrder(@PathVariable("cartId") Long cartId, @Valid @ModelAttribute OrdererInfo orderer,BindingResult result ,Principal principal) {
		if (result.hasErrors()) {
			return "redirect:/cart?fail";
		}
		ShoppingCart cart = cartService.findShoppingCartById(cartId);
		UserEntity user = userService.findByEmail(principal.getName());
		Order order = new Order();
		ordererService.save(orderer);
		order.setOrderer(orderer);
		order.setUser(user);
		for (CartItem item : cart.getCartItems()) {
			order.addOrderItem(item);
			Product product = productService.findProductById(item.getProductId());
			product.setStockQuantity(product.getStockQuantity() - item.getQuantity());
		}
		orderService.save(order);
		
		emailService.sendOrdererEmail(order);
		emailService.sendSellerEmail(order);
		
		user.setShopCart(null);
		cart.setUser(null);
		cartService.save(cart);
		return "redirect:/";
	}
}

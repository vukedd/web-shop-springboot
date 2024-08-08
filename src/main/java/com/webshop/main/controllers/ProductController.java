package com.webshop.main.controllers;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webshop.main.models.CartItem;
import com.webshop.main.models.Product;
import com.webshop.main.models.ShoppingCart;
import com.webshop.main.models.UserEntity;
import com.webshop.main.repositories.ProductRepository;
import com.webshop.main.security.CustomUserDetailsService;
import com.webshop.main.services.ProductService;
import com.webshop.main.services.ShoppingCartService;
import com.webshop.main.services.UserService;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class ProductController {
	
	private ProductService productService;
	private UserService userService;
	private ShoppingCartService cartService;

	public ProductController(ProductService productService, UserService userService, ShoppingCartService cartService) {
		super();
		this.productService = productService;
		this.userService = userService;
		this.cartService = cartService;
	}
	
	@GetMapping("/")
	public String getAllProducts(Model model, HttpServletRequest request) {
		List<Product> products = productService.findAllProducts();
		model.addAttribute("products", products);
		return "index.html";
	}
	
	@GetMapping("/products/{productId}")
	public String getProduct(@PathVariable("productId")Long productId ,Model model) {
		Product product = productService.findProductById(productId);
		List<Product> products = productService.findAllProducts();
		CartItem cartItem = new CartItem();
		model.addAttribute("cartItem", cartItem);
		model.addAttribute("product", product);
		model.addAttribute("products", products);
		return "product-details";
	}
	
	@PostMapping("/products/{productId}/addToCart")
	public String addToCart(@Valid @ModelAttribute CartItem cartItem, Principal principal, @PathVariable("productId") Long productId, BindingResult result, Model model) {
		UserEntity user = userService.findByEmail(principal.getName());
		Product product = productService.findProductById(productId);
		cartItem.setPhotoUrl(product.getPhotoUrl());
		cartItem.setProductId(productId);
		cartItem.setPrice(product.getPrice() * cartItem.getQuantity());
		if (user != null) {
			ShoppingCart shopCart = new ShoppingCart();
			user.setShopCart(shopCart);
			user.getShopCart().addItem(cartItem);
			cartItem.setShoppingCart(shopCart);
			cartService.save(shopCart);
		}
		return "redirect:/?success";
	}
}

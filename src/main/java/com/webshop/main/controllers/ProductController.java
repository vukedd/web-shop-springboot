package com.webshop.main.controllers;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webshop.main.dtos.ProductDto;
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
	public String getAllProducts(Model model, Principal principal) {
		List<Product> products = productService.findAllProducts();
		if (principal != null) {
			UserEntity user = userService.findByEmail(principal.getName());
			ShoppingCart cart = cartService.findShoppingCartByUserId(user);
			model.addAttribute("cart", cart);
		} else {
			ShoppingCart cart = null;
			model.addAttribute("cart", cart);
		}
		model.addAttribute("products", products);
		return "index";
	}
	
	@GetMapping("/products/{productId}")
	public String getProduct(@PathVariable("productId")Long productId , Principal principal,Model model) {
		Product product = productService.findProductById(productId);
		List<Product> products = productService.findAllProducts();
		CartItem cartItem = new CartItem();
		model.addAttribute("cartItem", cartItem);
		model.addAttribute("product", product);
		model.addAttribute("products", products);
		if (principal != null) {
			UserEntity user = userService.findByEmail(principal.getName());
			ShoppingCart cart = cartService.findShoppingCartByUserId(user);
			model.addAttribute("cart", cart);
		} else {
			ShoppingCart cart = null;
			model.addAttribute("cart", cart);
		}
		return "product-details";
	}
	
	@PostMapping("/products/{productId}/addToCart")
	public String addToCart(@Valid @ModelAttribute CartItem cartItem, Principal principal, @PathVariable("productId") Long productId, BindingResult result, Model model) {
		
		if (principal == null) {
			return "redirect:/login";
		}
		
		UserEntity user = userService.findByEmail(principal.getName());
		Product product = productService.findProductById(productId);
		cartService.addToShoppingCart(cartItem, product, user);
		
		return "redirect:/products/{productId}?success";
	}
	
	@GetMapping("/products")
	public String manageProducts(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "12") int size, Model model) {
		Page<Product> productPage = productService.findPaginated(page, size);
        model.addAttribute("productPage", productPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
		model.addAttribute("products", productPage.getContent());
		return "products";
	}
	
	@GetMapping("/products/add")
	public String newProduct(Model model) {
		model.addAttribute("productDto", new ProductDto());
		System.out.println(model.getAttribute("productDto"));
		return "product-add";
	}
	
	@PostMapping("/products/add/new")
	public String addNewProduct(@Valid @ModelAttribute("product") ProductDto newProduct, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "redirect:/products/add?failure";
		}
		Product product = productService.createProduct(newProduct);
		model.addAttribute("newProduct", product);
		
		return "redirect:/products?success";
	}
	
	@GetMapping("/products/{productId}/edit")
	public String editProduct(@PathVariable("productId") Long id, Model model) {
		Product product = productService.findProductById(id);
		ProductDto productDto = productService.mapToProductDto(product);
		model.addAttribute("product", productDto);
		
		return "product-edit";
	}
	
	@PostMapping("/products/{productId}/edit")
	public String confirmProductEdit(@PathVariable("productId") Long id, @ModelAttribute ProductDto product, BindingResult result, Model model) {
		Product newProduct = productService.mapToProduct(product);
		newProduct.setId(id);
		productService.updateProduct(newProduct);
		return "redirect:/products?editSuccess";
	}
	
	@GetMapping("/products/{productId}/delete")
	public String deleteProduct(@PathVariable("productId") Long id) {
		Product product = productService.findProductById(id);
		productService.deleteProduct(product);
		return "redirect:/products?delSuccess";
	}
}

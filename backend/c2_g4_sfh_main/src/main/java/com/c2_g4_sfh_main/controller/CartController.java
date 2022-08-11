package com.c2_g4_sfh_main.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c2_g4_sfh_main.entity.Cart;
import com.c2_g4_sfh_main.entity.ProductInOrder;
import com.c2_g4_sfh_main.entity.User;
import com.c2_g4_sfh_main.form.ItemForm;
import com.c2_g4_sfh_main.repository.IProductInOrderRepository;
import com.c2_g4_sfh_main.serviceInterface.ICartService;
import com.c2_g4_sfh_main.serviceInterface.IProductInOrderService;
import com.c2_g4_sfh_main.serviceInterface.IProductService;
import com.c2_g4_sfh_main.serviceInterface.IUserService;

import lombok.var;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	ICartService cartService;

	@Autowired
	IUserService userService;

	@Autowired
	IProductService productService;

	@Autowired
	IProductInOrderService productInOrderService;

	@Autowired
	IProductInOrderRepository productInOrderRepository;

	@PostMapping("")
	public ResponseEntity<Cart> mergeCart(@RequestBody Collection<ProductInOrder> productInOrders,
			Principal principal) {
		User user = userService.findOne(principal.getName());
		try {
			cartService.mergeLocalCart(productInOrders, user);
		} catch (Exception e) {
			ResponseEntity.badRequest().body("Merge Cart Failed");
		}
		return ResponseEntity.ok(cartService.getCart(user));
	}

	@GetMapping("")
	public Cart getCart(Principal principal) {
		User user = userService.findOne(principal.getName());
		return cartService.getCart(user);
	}

	@PostMapping("/add")
	public boolean addToCart(@RequestBody ItemForm form, Principal principal) {
		var productInfo = productService.findOne(form.getProductId());
		try {
			mergeCart(Collections.singleton(new ProductInOrder(productInfo, form.getQuantity())), principal);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@PutMapping("/{itemId}")
	public ProductInOrder modifyItem(@PathVariable("itemId") String itemId, @RequestBody Integer quantity,
			Principal principal) {
		User user = userService.findOne(principal.getName());
		productInOrderService.update(itemId, quantity, user);
		return productInOrderService.findOne(itemId, user);
	}

	@DeleteMapping("/{itemId}")
	public void deleteItem(@PathVariable("itemId") String itemId, Principal principal) {
		User user = userService.findOne(principal.getName());
		cartService.delete(itemId, user);
		// flush memory into DB
	}

	@PostMapping("/checkout")
	public ResponseEntity<?> checkout(Principal principal) {
		User user = userService.findOne(principal.getName());// Email as username
		cartService.checkout(user);
		return ResponseEntity.ok(null);
	}

}

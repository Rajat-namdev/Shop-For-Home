package com.wipro.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wipro.springboot.entity.Cart;
import com.wipro.springboot.entity.ProductInOrder;
import com.wipro.springboot.entity.User;
import com.wipro.springboot.form.ItemForm;
import com.wipro.springboot.repository.IProductInOrderRepository;
import com.wipro.springboot.service.ICartService;
import com.wipro.springboot.service.IProductInOrderService;
import com.wipro.springboot.service.IProductService;
import com.wipro.springboot.service.IUserService;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

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

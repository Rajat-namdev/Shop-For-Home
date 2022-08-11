package com.wipro.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import com.wipro.springboot.entity.Order;
import com.wipro.springboot.entity.ProductInOrder;
import com.wipro.springboot.service.IOrderService;
import com.wipro.springboot.service.IUserService;

import java.util.Collection;

@RestController
@CrossOrigin
public class OrderController {

	@Autowired
	IOrderService orderService;

	@Autowired
	IUserService userService;

	@GetMapping("/order")
	public Page<Order> orderList(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size, Authentication authentication) {
		PageRequest request = PageRequest.of(page - 1, size);
		Page<Order> orderPage;
		if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"))) {
			orderPage = orderService.findByBuyerEmail(authentication.getName(), request);
		} else {
			orderPage = orderService.findAll(request);
		}
		return orderPage;
	}

	@PatchMapping("/order/cancel/{id}")
	public ResponseEntity<Order> cancel(@PathVariable("id") Long orderId, Authentication authentication) {
		Order order = orderService.findOne(orderId);
		if (!authentication.getName().equals(order.getBuyerEmail())
				&& authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"))) {

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return ResponseEntity.ok(orderService.cancel(orderId));
	}

	@PatchMapping("/order/finish/{id}")
	public ResponseEntity<Order> finish(@PathVariable("id") Long orderId, Authentication authentication) {
		if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"))) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return ResponseEntity.ok(orderService.finish(orderId));
	}

	@GetMapping("/order/{id}")
	public ResponseEntity show(@PathVariable("id") Long orderId, Authentication authentication) {
		boolean isCustomer = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
		Order order = orderService.findOne(orderId);
		if (isCustomer && !authentication.getName().equals(order.getBuyerEmail())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		Collection<ProductInOrder> items = order.getProducts();
		return ResponseEntity.ok(order);
	}
}

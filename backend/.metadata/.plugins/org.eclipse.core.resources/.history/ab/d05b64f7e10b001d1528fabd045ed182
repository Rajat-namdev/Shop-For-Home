package com.wipro.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.springboot.entity.Cart;
import com.wipro.springboot.entity.Order;
import com.wipro.springboot.entity.ProductInOrder;
import com.wipro.springboot.entity.User;
import com.wipro.springboot.repository.ICartRepository;
import com.wipro.springboot.repository.IOrderRepository;
import com.wipro.springboot.repository.IProductInOrderRepository;
import com.wipro.springboot.repository.IUserRepository;
import com.wipro.springboot.service.ICartService;
import com.wipro.springboot.service.IProductService;
import com.wipro.springboot.service.IUserService;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class CartServiceImpl implements ICartService {

	@Autowired
	IProductService productService;

	@Autowired
	IUserService userService;

	@Autowired
	IOrderRepository orderRepository;

	@Autowired
	IUserRepository userRepository;

	@Autowired
	IProductInOrderRepository productInOrderRepository;

	@Autowired
	ICartRepository cartRepository;

	@Override
	public Cart getCart(User user) {
		return user.getCart();
	}

	@Override
	@Transactional
	public void mergeLocalCart(Collection<ProductInOrder> productInOrders, User user) {
		Cart finalCart = user.getCart();
		productInOrders.forEach(productInOrder -> {
			Set<ProductInOrder> set = finalCart.getProducts();
			Optional<ProductInOrder> old = set.stream()
					.filter(e -> e.getProductId().equals(productInOrder.getProductId())).findFirst();
			ProductInOrder prod;
			if (old.isPresent()) {
				prod = old.get();
				prod.setCount(productInOrder.getCount() + prod.getCount());
			} else {
				prod = productInOrder;
				prod.setCart(finalCart);
				finalCart.getProducts().add(prod);
			}
			productInOrderRepository.save(prod);
		});
		cartRepository.save(finalCart);

	}

	@Override
	@Transactional
	public void delete(String itemId, User user) {
		var op = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
		op.ifPresent(productInOrder -> {
			productInOrder.setCart(null);
			productInOrderRepository.deleteById(productInOrder.getId());
		});
	}

	@Override
	@Transactional
	public void checkout(User user) {
		Order order = new Order(user);
		orderRepository.save(order);

		user.getCart().getProducts().forEach(productInOrder -> {
			productInOrder.setCart(null);
			productInOrder.setOrder(order);
			productService.decreaseStock(productInOrder.getProductId(), productInOrder.getCount());
			productInOrderRepository.save(productInOrder);
		});

	}

}

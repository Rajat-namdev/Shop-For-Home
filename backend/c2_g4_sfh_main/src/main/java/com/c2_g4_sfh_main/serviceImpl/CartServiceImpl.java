package com.c2_g4_sfh_main.serviceImpl;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.c2_g4_sfh_main.entity.Cart;
import com.c2_g4_sfh_main.entity.Order;
import com.c2_g4_sfh_main.entity.ProductInOrder;
import com.c2_g4_sfh_main.entity.User;
import com.c2_g4_sfh_main.repository.ICartRepository;
import com.c2_g4_sfh_main.repository.IOrderRepository;
import com.c2_g4_sfh_main.repository.IProductInOrderRepository;
import com.c2_g4_sfh_main.repository.IUserRepository;
import com.c2_g4_sfh_main.serviceInterface.ICartService;
import com.c2_g4_sfh_main.serviceInterface.IProductService;
import com.c2_g4_sfh_main.serviceInterface.IUserService;

import lombok.var;

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

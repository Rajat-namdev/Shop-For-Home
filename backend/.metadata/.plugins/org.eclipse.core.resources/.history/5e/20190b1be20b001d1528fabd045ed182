package com.wipro.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.springboot.entity.ProductInOrder;
import com.wipro.springboot.entity.User;
import com.wipro.springboot.repository.IProductInOrderRepository;
import com.wipro.springboot.service.IProductInOrderService;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class ProductInOrderServiceImpl implements IProductInOrderService {

	@Autowired
	IProductInOrderRepository productInOrderRepository;

	@Override
	@Transactional
	public void update(String itemId, Integer quantity, User user) {
		var op = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
		op.ifPresent(productInOrder -> {
			productInOrder.setCount(quantity);
			productInOrderRepository.save(productInOrder);
		});

	}

	@Override
	public ProductInOrder findOne(String itemId, User user) {
		var op = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
		AtomicReference<ProductInOrder> res = new AtomicReference<>();
		op.ifPresent(res::set);
		return res.get();
	}
}

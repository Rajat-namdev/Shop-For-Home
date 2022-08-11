package com.c2_g4_sfh_main.serviceImpl;

import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.c2_g4_sfh_main.entity.ProductInOrder;
import com.c2_g4_sfh_main.entity.User;
import com.c2_g4_sfh_main.repository.IProductInOrderRepository;
import com.c2_g4_sfh_main.serviceInterface.IProductInOrderService;

import lombok.var;

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

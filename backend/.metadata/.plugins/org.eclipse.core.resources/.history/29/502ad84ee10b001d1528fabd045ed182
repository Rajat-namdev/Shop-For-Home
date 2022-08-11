package com.wipro.springboot.service;

import java.util.Collection;

import com.wipro.springboot.entity.Cart;
import com.wipro.springboot.entity.ProductInOrder;
import com.wipro.springboot.entity.User;

public interface ICartService {

	Cart getCart(User user);

	void mergeLocalCart(Collection<ProductInOrder> productInOrders, User user);

	void delete(String itemId, User user);

	void checkout(User user);
}

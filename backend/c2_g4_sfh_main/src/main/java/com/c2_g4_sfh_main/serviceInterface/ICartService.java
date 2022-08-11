package com.c2_g4_sfh_main.serviceInterface;

import java.util.Collection;

import com.c2_g4_sfh_main.entity.Cart;
import com.c2_g4_sfh_main.entity.ProductInOrder;
import com.c2_g4_sfh_main.entity.User;

public interface ICartService {

	Cart getCart(User user);

	void mergeLocalCart(Collection<ProductInOrder> productInOrders, User user);

	void delete(String itemId, User user);

	void checkout(User user);
}

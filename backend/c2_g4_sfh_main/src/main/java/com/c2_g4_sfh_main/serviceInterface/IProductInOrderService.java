package com.c2_g4_sfh_main.serviceInterface;

import com.c2_g4_sfh_main.entity.ProductInOrder;
import com.c2_g4_sfh_main.entity.User;

public interface IProductInOrderService {

	void update(String itemId, Integer quantity, User user);

	ProductInOrder findOne(String itemId, User user);
}

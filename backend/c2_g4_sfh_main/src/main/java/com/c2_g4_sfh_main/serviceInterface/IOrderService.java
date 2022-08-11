package com.c2_g4_sfh_main.serviceInterface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.c2_g4_sfh_main.entity.Order;

public interface IOrderService {
	Page<Order> findAll(Pageable pageable);

	Page<Order> findByStatus(Integer status, Pageable pageable);

	Page<Order> findByBuyerEmail(String email, Pageable pageable);

	Page<Order> findByBuyerPhone(String phone, Pageable pageable);

	Order findOne(Long orderId);

	Order finish(Long orderId);

	Order cancel(Long orderId);

}

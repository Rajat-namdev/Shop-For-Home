package com.c2_g4_sfh_main.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c2_g4_sfh_main.entity.Order;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer> {
	
	Order findByOrderId(Long orderId);

	Page<Order> findAllByOrderStatusOrderByCreateTimeDesc(Integer orderStatus, Pageable pageable);

	Page<Order> findAllByBuyerEmailOrderByOrderStatusAscCreateTimeDesc(String buyerEmail, Pageable pageable);

	Page<Order> findAllByOrderByOrderStatusAscCreateTimeDesc(Pageable pageable);

	Page<Order> findAllByBuyerPhoneOrderByOrderStatusAscCreateTimeDesc(String buyerPhone, Pageable pageable);
}

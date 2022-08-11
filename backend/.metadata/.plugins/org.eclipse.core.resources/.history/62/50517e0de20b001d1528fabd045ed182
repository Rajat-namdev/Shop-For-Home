package com.wipro.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.springboot.entity.Order;
import com.wipro.springboot.entity.Product;
import com.wipro.springboot.entity.ProductInOrder;
import com.wipro.springboot.enums.OrderStatusEnum;
import com.wipro.springboot.enums.ResultEnum;
import com.wipro.springboot.exception.MyException;
import com.wipro.springboot.repository.IOrderRepository;
import com.wipro.springboot.repository.IProductInOrderRepository;
import com.wipro.springboot.repository.IProductRepository;
import com.wipro.springboot.repository.IUserRepository;
import com.wipro.springboot.service.IOrderService;
import com.wipro.springboot.service.IProductService;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	IOrderRepository orderRepository;

	@Autowired
	IUserRepository userRepository;

	@Autowired
	IProductRepository productInfoRepository;

	@Autowired
	IProductService productService;

	@Autowired
	IProductInOrderRepository productInOrderRepository;

	@Override
	public Page<Order> findAll(Pageable pageable) {
		return orderRepository.findAllByOrderByOrderStatusAscCreateTimeDesc(pageable);
	}

	@Override
	public Page<Order> findByStatus(Integer status, Pageable pageable) {
		return orderRepository.findAllByOrderStatusOrderByCreateTimeDesc(status, pageable);
	}

	@Override
	public Page<Order> findByBuyerEmail(String email, Pageable pageable) {
		return orderRepository.findAllByBuyerEmailOrderByOrderStatusAscCreateTimeDesc(email, pageable);
	}

	@Override
	public Page<Order> findByBuyerPhone(String phone, Pageable pageable) {
		return orderRepository.findAllByBuyerPhoneOrderByOrderStatusAscCreateTimeDesc(phone, pageable);
	}

	@Override
	public Order findOne(Long orderId) {
		Order orderMain = orderRepository.findByOrderId(orderId);
		if (orderMain == null) {
			throw new MyException(ResultEnum.ORDER_NOT_FOUND);
		}
		return orderMain;
	}

	@Override
	@Transactional
	public Order finish(Long orderId) {
		Order order = findOne(orderId);
		if (!order.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
			throw new MyException(ResultEnum.ORDER_STATUS_ERROR);
		}

		order.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
		orderRepository.save(order);
		return orderRepository.findByOrderId(orderId);
	}

	@Override
	@Transactional
	public Order cancel(Long orderId) {
		Order orderMain = findOne(orderId);
		if (!orderMain.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
			throw new MyException(ResultEnum.ORDER_STATUS_ERROR);
		}

		orderMain.setOrderStatus(OrderStatusEnum.CANCELED.getCode());
		orderRepository.save(orderMain);

		Iterable<ProductInOrder> products = orderMain.getProducts();
		for (ProductInOrder productInOrder : products) {
			Product product = productInfoRepository.findByProductId(productInOrder.getProductId());
			if (product != null) {
				productService.increaseStock(productInOrder.getProductId(), productInOrder.getCount());
			}
		}
		return orderRepository.findByOrderId(orderId);

	}
}

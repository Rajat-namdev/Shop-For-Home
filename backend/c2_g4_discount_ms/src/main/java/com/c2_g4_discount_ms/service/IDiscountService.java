package com.c2_g4_discount_ms.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.c2_g4_discount_ms.entity.Discount;

public interface IDiscountService {
	
	Discount createCoupon(String code);

	Page<Discount> findAll(PageRequest request);

	void deleteCoupon(String code);

	List<Discount> findAll();

}

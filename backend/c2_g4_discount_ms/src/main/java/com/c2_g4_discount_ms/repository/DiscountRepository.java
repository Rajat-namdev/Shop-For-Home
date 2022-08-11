package com.c2_g4_discount_ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c2_g4_discount_ms.entity.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, String> {

}

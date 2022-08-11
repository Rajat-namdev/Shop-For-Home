package com.c2_g4_sfh_main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c2_g4_sfh_main.entity.Cart;

@Repository
public interface ICartRepository extends JpaRepository<Cart, Integer> {
}

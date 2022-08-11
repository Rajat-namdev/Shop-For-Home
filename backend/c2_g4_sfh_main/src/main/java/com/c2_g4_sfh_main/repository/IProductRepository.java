package com.c2_g4_sfh_main.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c2_g4_sfh_main.entity.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, String> {
	Product findByProductId(String id);

	Page<Product> findAllByProductStatusOrderByProductIdAsc(Integer productStatus, Pageable pageable);

	Page<Product> findAllByCategoryTypeOrderByProductIdAsc(Integer categoryType, Pageable pageable);

	Page<Product> findAllByOrderByProductId(Pageable pageable);

}

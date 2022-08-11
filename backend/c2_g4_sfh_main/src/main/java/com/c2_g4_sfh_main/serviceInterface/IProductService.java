package com.c2_g4_sfh_main.serviceInterface;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.c2_g4_sfh_main.entity.Product;

public interface IProductService {

	Product findOne(String productId);

	Page<Product> findUpAll(Pageable pageable);

	Page<Product> findAll(Pageable pageable);

	Page<Product> findAllInCategory(Integer categoryType, Pageable pageable);

	void increaseStock(String productId, int amount);

	void decreaseStock(String productId, int amount);

	Product offSale(String productId);

	Product onSale(String productId);

	Product update(Product product);

	Product save(Product product);

	void delete(String productId);

	List<Product> findAll();

}

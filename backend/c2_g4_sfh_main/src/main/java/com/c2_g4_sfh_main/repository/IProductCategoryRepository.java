package com.c2_g4_sfh_main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c2_g4_sfh_main.entity.ProductCategory;

@Repository
public interface IProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
	List<ProductCategory> findByCategoryTypeInOrderByCategoryTypeAsc(List<Integer> categoryTypes);

	List<ProductCategory> findAllByOrderByCategoryType();

	ProductCategory findByCategoryType(Integer categoryType);
}

package com.c2_g4_sfh_main.serviceInterface;

import java.util.List;

import com.c2_g4_sfh_main.entity.ProductCategory;

public interface IProductCategoryService {

	List<ProductCategory> findAll();

	ProductCategory findByCategoryType(Integer categoryType);

	List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

	ProductCategory save(ProductCategory productCategory);

}

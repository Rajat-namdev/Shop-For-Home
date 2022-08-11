package com.c2_g4_sfh_main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.c2_g4_sfh_main.entity.Product;
import com.c2_g4_sfh_main.entity.ProductCategory;
import com.c2_g4_sfh_main.response.CategoryPage;
import com.c2_g4_sfh_main.serviceInterface.IProductCategoryService;
import com.c2_g4_sfh_main.serviceInterface.IProductService;

import lombok.var;

@RestController
@CrossOrigin
public class ProductCategoryController {
	@Autowired
	IProductCategoryService categoryService;
	@Autowired
	IProductService productService;

	@GetMapping("/category/{type}")
	public CategoryPage showOne(@PathVariable("type") Integer categoryType,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "9") Integer size) {

		ProductCategory cat = categoryService.findByCategoryType(categoryType);
		PageRequest request = PageRequest.of(page - 1, size);
		Page<Product> productInCategory = productService.findAllInCategory(categoryType, request);
		var tmp = new CategoryPage("", productInCategory);
		tmp.setCategory(cat.getCategoryName());
		return tmp;
	}
}

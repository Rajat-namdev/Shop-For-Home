package com.wipro.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.wipro.springboot.entity.Product;
import com.wipro.springboot.entity.ProductCategory;
import com.wipro.springboot.service.IProductCategoryService;
import com.wipro.springboot.service.IProductService;
import com.wipro.springboot.vo.response.CategoryPage;

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

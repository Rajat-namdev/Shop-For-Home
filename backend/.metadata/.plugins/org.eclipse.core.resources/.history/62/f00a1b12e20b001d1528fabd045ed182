package com.wipro.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.springboot.entity.ProductCategory;
import com.wipro.springboot.enums.ResultEnum;
import com.wipro.springboot.exception.MyException;
import com.wipro.springboot.repository.IProductCategoryRepository;
import com.wipro.springboot.service.IProductCategoryService;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {
	@Autowired
	IProductCategoryRepository productCategoryRepository;

	@Override
	public List<ProductCategory> findAll() {
		List<ProductCategory> res = productCategoryRepository.findAllByOrderByCategoryType();
		return res;
	}

	@Override
	public ProductCategory findByCategoryType(Integer categoryType) {
		ProductCategory res = productCategoryRepository.findByCategoryType(categoryType);
		if (res == null)
			throw new MyException(ResultEnum.CATEGORY_NOT_FOUND);
		return res;
	}

	@Override
	public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
		List<ProductCategory> res = productCategoryRepository
				.findByCategoryTypeInOrderByCategoryTypeAsc(categoryTypeList);
		return res;
	}

	@Override
	@Transactional
	public ProductCategory save(ProductCategory productCategory) {
		return productCategoryRepository.save(productCategory);
	}

}

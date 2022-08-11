package com.c2_g4_sfh_main.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.c2_g4_sfh_main.entity.ProductCategory;
import com.c2_g4_sfh_main.enums.ResultEnum;
import com.c2_g4_sfh_main.exception.MyException;
import com.c2_g4_sfh_main.repository.IProductCategoryRepository;
import com.c2_g4_sfh_main.serviceInterface.IProductCategoryService;

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

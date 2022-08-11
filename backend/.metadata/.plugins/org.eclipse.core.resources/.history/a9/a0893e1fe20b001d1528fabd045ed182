package com.wipro.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.springboot.entity.Product;
import com.wipro.springboot.enums.ProductStatusEnum;
import com.wipro.springboot.enums.ResultEnum;
import com.wipro.springboot.exception.MyException;
import com.wipro.springboot.repository.IProductRepository;
import com.wipro.springboot.service.IProductCategoryService;
import com.wipro.springboot.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	IProductRepository productRepository;

	@Autowired
	IProductCategoryService categoryService;

	@Override
	public Product findOne(String productId) {

		Product product = productRepository.findByProductId(productId);
		return product;
	}

	@Override
	public Page<Product> findUpAll(Pageable pageable) {
		return productRepository.findAllByProductStatusOrderByProductIdAsc(ProductStatusEnum.UP.getCode(), pageable);
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAllByOrderByProductId(pageable);
	}

	@Override
	public Page<Product> findAllInCategory(Integer categoryType, Pageable pageable) {
		return productRepository.findAllByCategoryTypeOrderByProductIdAsc(categoryType, pageable);
	}

	@Override
	@Transactional
	public void increaseStock(String productId, int amount) {
		Product product = findOne(productId);
		if (product == null)
			throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

		int update = product.getProductStock() + amount;
		product.setProductStock(update);
		productRepository.save(product);
	}

	@Override
	@Transactional
	public void decreaseStock(String productId, int amount) {
		Product product = findOne(productId);
		if (product == null)
			throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

		int update = product.getProductStock() - amount;
		if (update <= 0)
			throw new MyException(ResultEnum.PRODUCT_NOT_ENOUGH);

		product.setProductStock(update);
		productRepository.save(product);
	}

	@Override
	@Transactional
	public Product offSale(String productId) {
		Product product = findOne(productId);
		if (product == null)
			throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

		if (product.getProductStatus() == ProductStatusEnum.DOWN.getCode()) {
			throw new MyException(ResultEnum.PRODUCT_STATUS_ERROR);
		}

		product.setProductStatus(ProductStatusEnum.DOWN.getCode());
		return productRepository.save(product);
	}

	@Override
	@Transactional
	public Product onSale(String productId) {
		Product product = findOne(productId);
		if (product == null)
			throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

		if (product.getProductStatus() == ProductStatusEnum.UP.getCode()) {
			throw new MyException(ResultEnum.PRODUCT_STATUS_ERROR);
		}

		product.setProductStatus(ProductStatusEnum.UP.getCode());
		return productRepository.save(product);
	}

	@Override
	public Product update(Product product) {

		categoryService.findByCategoryType(product.getCategoryType());
		if (product.getProductStatus() > 1) {
			throw new MyException(ResultEnum.PRODUCT_STATUS_ERROR);
		}

		return productRepository.save(product);
	}

	@Override
	public Product save(Product product) {
		return update(product);
	}

	@Override
	public void delete(String productId) {
		Product product = findOne(productId);
		if (product == null)
			throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);
		productRepository.delete(product);

	}

	@Override
	@Transactional
	public List<Product> findAll() {

		return productRepository.findAll();
	}

}

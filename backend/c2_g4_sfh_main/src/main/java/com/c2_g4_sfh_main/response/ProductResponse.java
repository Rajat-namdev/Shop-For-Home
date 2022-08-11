package com.c2_g4_sfh_main.response;

import java.util.List;

import com.c2_g4_sfh_main.entity.Product;


public class ProductResponse {

	private List<Product> productList;

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

}

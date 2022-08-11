package com.c2_g4_sfh_main.response;

import org.springframework.data.domain.Page;

import com.c2_g4_sfh_main.entity.Product;

public class CategoryPage {
	private String category;
	private Page<Product> page;

	public CategoryPage(String category, Page<Product> page) {
		this.category = category;
		this.page = page;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Page<Product> getPage() {
		return page;
	}

	public void setPage(Page<Product> page) {
		this.page = page;
	}
}

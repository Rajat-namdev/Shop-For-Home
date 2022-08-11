package com.wipro.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.springboot.entity.Product;
import com.wipro.springboot.service.IProductCategoryService;
import com.wipro.springboot.service.IProductService;
import com.wipro.springboot.vo.response.ProductResponse;

@CrossOrigin
@RestController
public class ProductController {

	@Autowired
	IProductCategoryService categoryService;

	@Autowired
	IProductService productService;

	@GetMapping("/product")
	public Page<Product> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "9") Integer size) {
		PageRequest request = PageRequest.of(page - 1, size);
		return productService.findAll(request);
	}

	@GetMapping("/productall")
	public ProductResponse findAll() {
		ProductResponse response = new ProductResponse();
		response.setProductList(productService.findAll());
		return response;
	}

	@GetMapping("/product/{productId}")
	public Product showOne(@PathVariable("productId") String productId) {

		Product product = productService.findOne(productId);

		return product;
	}

	@PostMapping("/seller/product/new")
	public ResponseEntity create(@Validated @RequestBody Product product, BindingResult bindingResult) {
		Product productIdExists = productService.findOne(product.getProductId());
		if (productIdExists != null) {
			bindingResult.rejectValue("productId", "error.product",
					"There is already a product with the code provided");
		}
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(bindingResult);
		}
		return ResponseEntity.ok(productService.save(product));
	}

	@PutMapping("/seller/product/{id}/edit")
	public ResponseEntity edit(@PathVariable("id") String productId, @Validated @RequestBody Product product,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(bindingResult);
		}
		if (!productId.equals(product.getProductId())) {
			return ResponseEntity.badRequest().body("Id Not Matched");
		}

		return ResponseEntity.ok(productService.update(product));
	}

	@DeleteMapping("/seller/product/{id}/delete")
	public ResponseEntity delete(@PathVariable("id") String productId) {
		productService.delete(productId);
		return ResponseEntity.ok().build();
	}

}

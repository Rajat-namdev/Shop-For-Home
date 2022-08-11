package com.wipro.springboot.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wipro.springboot.entity.Product;
import com.wipro.springboot.repository.IProductRepository;
import com.wipro.springboot.vo.helper.CSVHelper;

@Service
public class CSVServiceImpl {

	@Autowired
	IProductRepository repository;

	public void save(MultipartFile file) {
		try {
			List<Product> tutorials = CSVHelper.csvToTutorials(file.getInputStream());
			repository.saveAll(tutorials);
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}

	public List<Product> getAllTutorials() {
		return repository.findAll();
	}

}

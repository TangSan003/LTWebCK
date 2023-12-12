package com.poly.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.poly.entity.Product;

public interface ProductService {

	List<Product> findByCategoryId(Integer integer);
	List<Product> findByLaptop();
	List<Product> findAll();


	Product findById(Integer id);

	Product create(Product product);

	Product update(Product product);

	void delete(Integer id);

	List<Product> findByNameContaining(String name);

	List<Product> findByKeywords(String keywords);

	List<Product> findByTrademarkId(Integer integer);
	public File save(MultipartFile file, String path) throws IOException;
	
	
	
	

}

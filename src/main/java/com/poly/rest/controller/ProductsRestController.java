package com.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entity.Product;
import com.poly.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductsRestController {
	@Autowired
	ProductService productService;
	
	@GetMapping()
	public List<Product>  getAll() {
		return productService.findAll();
	}
	
	@GetMapping("{product_id}")
	public Product getOne(@PathVariable("product_id") Integer product_id) {
		return productService.findById(product_id);
	}
	
	@PostMapping
	public Product create(@RequestBody Product product) {
		return productService.create(product);
	}
	
	@PutMapping("{product_id}")
	public Product update(@PathVariable("product_id") 	Integer product_id
			,@RequestBody Product product) {
		return productService.update(product);
	}
	
	@DeleteMapping("{product_id}")
	public void delete(@PathVariable("product_id") Integer product_id) {
		 productService.delete(product_id);
	}
}

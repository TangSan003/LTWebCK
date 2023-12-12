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

import com.poly.entity.Category;
import com.poly.service.CategoryService;
import com.poly.entity.Product;
import com.poly.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
public class CategoryRestController {
	@Autowired
	CategoryService categoryService;
	@GetMapping()
	public List<Category> getAll() {
		return categoryService.findAll();
	}
	@GetMapping("{category_id}")
	public Category getOne(@PathVariable("category_id")Integer category_id) {
		return categoryService.findById(category_id);
	}
	@PostMapping()
	public Category create(@RequestBody Category category_id) {
		return categoryService.create(category_id);
	}
	@PutMapping("{category_id}")
	public Category put(@PathVariable("category_id")Integer category_id,@RequestBody Category category) {
		return categoryService.update(category);
	}
	@DeleteMapping("{category_id}")
	public void delete(@PathVariable("category_id")Integer category_id) {
		categoryService.delete(category_id);
	}
}

package com.poly.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.entity.Favorite;
import com.poly.entity.Order;
import com.poly.service.FavoriteService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/favorites")
public class FavoriteRestController {
	@Autowired
	FavoriteService dao;
	
	@Autowired
	HttpServletRequest request;
	
	@GetMapping
	public List<Favorite> getAll(){
		return dao.findByAllDesc();
	}
	
	
	
	@GetMapping("{product_id}/{username}")
	public List<Favorite> getCheckisShow(@PathVariable("product_id") Integer product_id ,
			@PathVariable("username") String username){
		
		return  dao.checkFavaoriteAdmin(product_id, username);
		
	}
	@PostMapping()
	public Favorite create(@RequestBody Favorite favoriteData) {
		return dao.create(favoriteData);
	}
	
	@DeleteMapping("{favorite_id}")
	public void delete(@PathVariable("favorite_id") Integer favorite_id) {
		 dao.delete(favorite_id);
	}
	
	@DeleteMapping("{product_id}/{username}")
	public void deleteFavoriteAdmin(@PathVariable("product_id") Integer product_id , 
			@PathVariable("username") String username) {
		 dao.deleteFavoriteAdmin(product_id, username);
	}
}

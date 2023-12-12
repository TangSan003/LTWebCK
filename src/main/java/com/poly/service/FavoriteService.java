package com.poly.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import com.poly.entity.Favorite;

public interface FavoriteService {
	
	List<Favorite> findByAllDesc();
	
	List<Favorite> checkFavaoriteAdmin(Integer productid, String username);
	
	Favorite create(Favorite favorite);
	 
	Favorite update(Favorite favorite);
	
	void deleteFavoriteAdmin(Integer productid, String username);
	
	void delete(Integer favorite_id);
}

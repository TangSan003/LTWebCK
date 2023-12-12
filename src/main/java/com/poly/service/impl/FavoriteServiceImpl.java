package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.dao.FavoriteDao;
import com.poly.entity.Favorite;

import com.poly.service.FavoriteService;

@Service
public class FavoriteServiceImpl implements FavoriteService{
	
	@Autowired
	FavoriteDao dao;
	
	@Override
	public List<Favorite> findByAllDesc() {
		return dao.findByAllDesc();
	}


	@Override
	public Favorite create(Favorite favorite) {
		return dao.save(favorite);
	}

	@Override
	public Favorite update(Favorite favorite) {
		
		return dao.save(favorite);
	}

	@Override
	public void delete(Integer favorite_id) {
		dao.deleteById(favorite_id);
	}


	@Override
	public void deleteFavoriteAdmin(Integer productid, String username) {
		dao.deleteFavaritesAdmin(productid, username);
	}


	@Override
	public List<Favorite> checkFavaoriteAdmin(Integer productid, String username) {
		return dao.checkFavaritesAdmin(productid, username);
	}


	
	
	

}

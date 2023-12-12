package com.poly.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.entity.Order;

public interface OrderService {


	Order create(JsonNode orderData);

	Order findById(Integer id);

	List<Order> findByUsername(String username);
	
	List<Order> findByAllDesc();

	void deleteById(Integer id);

	Order update(Order order);
}

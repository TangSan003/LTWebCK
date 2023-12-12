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

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.dao.OrderDao;
import com.poly.entity.Order;
import com.poly.service.OrderService;

@CrossOrigin("*")
@RestController
public class OrderRestController {
	@Autowired
	OrderDao odao;
	@Autowired
	OrderService orderService;

	@GetMapping("/rest/orders")
	public List<Order> getAll(){
		return orderService.findByAllDesc();
	}
	@GetMapping("/rest/ordersstatus")
	public List<Order> getStatus(){
		return odao.getStatus();
	}
	
	@PostMapping("/rest/orders")
	public Order create(@RequestBody JsonNode orderData) {
		return orderService.create(orderData);
	}
	
	@PutMapping("/rest/orders/{id}")
	public Order put(@PathVariable("id")Integer id,@RequestBody Order order) {
		return  orderService.update(order);
	}
}
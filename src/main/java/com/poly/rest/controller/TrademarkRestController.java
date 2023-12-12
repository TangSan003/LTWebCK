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
import com.poly.entity.Trademark;
import com.poly.service.CategoryService;
import com.poly.service.TrademarkService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/trademarks")
public class TrademarkRestController {
	@Autowired
	TrademarkService trademarkService;
	@GetMapping()
	public List<Trademark> getAll() {
		return trademarkService.findAll();
	}
	@GetMapping("{trademark_id}")
	public Trademark getOne(@PathVariable("trademark_id")Integer trademark_id) {
		return trademarkService.findById(trademark_id);
	}
	@PostMapping()
	public Trademark create(@RequestBody Trademark trademark_id) {
		return trademarkService.create(trademark_id);
	}
	@PutMapping("{trademark_id}")
	public Trademark put(@PathVariable("trademark_id")Integer trademark_id,@RequestBody Trademark trademark) {
		return trademarkService.update(trademark);
	}
	@DeleteMapping("{trademark_id}")
	public void delete(@PathVariable("id")Integer trademark_id) {
		trademarkService.delete(trademark_id);
	}
}

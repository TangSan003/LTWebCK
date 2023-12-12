package com.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.poly.dao.AccountDao;
import com.poly.dao.AuthorityDao;
import com.poly.dao.OrderDetailDao;
import com.poly.entity.OrderDetail;
import com.poly.service.AccountService;
import com.poly.service.AuthorityService;
import com.poly.service.OrderDetailService;



@Service
public class OrderDetailServiceImlq implements OrderDetailService {
	@Autowired
	 OrderDetailDao dao;

	@Override
	public List<OrderDetail> findByOrderID(Integer orderid) {
		return dao.findByOrderID(orderid);
	}

	

	

	
}

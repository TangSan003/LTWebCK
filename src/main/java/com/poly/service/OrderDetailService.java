package com.poly.service;

import java.util.List;

import com.poly.entity.OrderDetail;

public interface OrderDetailService {

	List<OrderDetail> findByOrderID(Integer orderid);

}

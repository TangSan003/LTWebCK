package com.poly.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Orderdetails")
public class OrderDetail implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer Orderdetail_id;
	Double Price;
	Integer Quantity;
	@ManyToOne
	@JoinColumn(name = "Product_id")
	Product product;
	@ManyToOne
	@JoinColumn(name = "Order_id")
	Order order;
}

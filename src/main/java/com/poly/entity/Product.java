package com.poly.entity;

import java.util.Date;
import java.util.List;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;


@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Products")
public class Product implements Serializable {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer Product_id;
	String Name;
	String Image1;
	String Image2 = null;
	String Image3 = null;
	String Detail ;
	String Description;
	String Color;
	String Material;
	String Size;
	Double Unit_price;
	Integer Quantity;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date Product_date = new Date();
	Double Distcount ;
	Boolean Status = false;
	Boolean Special = false;
	Boolean Lastest = false;
	@ManyToOne
	@JoinColumn(name = "Category_id")
	Category category;
	@ManyToOne
	@JoinColumn(name = "Trademark_id")
	Trademark trademark;
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderDetails;	
	
	
}

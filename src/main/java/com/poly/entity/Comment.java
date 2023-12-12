package com.poly.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Comments")
public class Comment {
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer Comment_id;
	String Image =  null ;
	String Comment_Content;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "Comment_date")
	Date Comment_date = new Date();
	@ManyToOne
	@JoinColumn(name = "Product_id")
	Product product;
	@ManyToOne
	@JoinColumn(name = "Username")
	Account account;
}

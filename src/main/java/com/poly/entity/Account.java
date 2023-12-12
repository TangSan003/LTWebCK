package com.poly.entity;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Accounts")
public class Account {
	@Id
			@Column(length = 50)
	String Username;
	String Password;
	String Fullname;	
	String Email;
	String Photo;
	String Phone;
	Boolean active;
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Order> Orders;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	List<Authority> Authorities;
}

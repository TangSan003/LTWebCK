package com.poly.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Authorities", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"Username", "Role_id"})
})
public class Authority {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Authorize_id;
	@ManyToOne @JoinColumn(name = "Username")
	private Account account;
	@ManyToOne  @JoinColumn(name = "Role_id")
	private Role role;
}

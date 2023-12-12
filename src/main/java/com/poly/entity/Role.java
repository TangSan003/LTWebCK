package com.poly.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "roles")
public class Role {
	@Id
	@Column(length = 50)

	private String Role_id;
	private String Name;
	@JsonIgnore
	@OneToMany(mappedBy = "role")
	List<Authority> Authorities;
}

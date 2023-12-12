package com.poly.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
	@Id
	@Column(length = 50)

	Serializable group;
	Double sum;
	Long count;
	Double min;
	Double max;
	Double avg;
	
	
}

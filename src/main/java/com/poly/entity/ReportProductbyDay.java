package com.poly.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportProductbyDay implements Serializable  {
	@Id
	Integer Product_id;
	String Name;
	Double sum;
	Long count;
}


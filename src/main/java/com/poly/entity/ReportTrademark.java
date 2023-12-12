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
public class ReportTrademark implements Serializable {
	@Id
	Integer trademark_id;
	String trademark_name;
	Double sum;
	Long count;
}

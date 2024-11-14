package com.ecom.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="category_table")
public class Category {

	
	@Id
	@GeneratedValue(generator = "category")
	@SequenceGenerator(name = "category", sequenceName = "category_seq")
	@Column(name = "catg_id_col")
	private Integer id;
	
	@Column(name = "catg_name_col")
	private String categoryName;
	
	
	@Column(name = "catg_desc_col")
	private String categoryDesc;
	
	
	
	
}

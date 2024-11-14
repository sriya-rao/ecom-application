package com.ecom.app.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "product_table")
public class Product {

	@Id
	@GeneratedValue(generator = "product")
	@SequenceGenerator(name = "product" ,sequenceName = "product_seq")
	@Column(name = "prod_id_col")
	private Integer id;
	
	@Column(name = "prod_title_col")
	private String prodTitle;
	
	@Column(name = "prod_desc_col",length = 3000)
	private String prodDesc;
	
	
    
	@Column(name = "prod_pic_id_col")
	private String prodPicName;
	
	@Column(name = "prod_price_col")
	private Double prodPrice;
	
	@Column(name = "prod_disc_col")
	private Integer prodDiscount;
	
	@Column(name = "prod_qty_col")
	private String prodQty;
	
	@Column(name = "prod_disc_price_col")
	private Double discountedPrice;
	
	@ManyToOne
	@JoinColumn(name = "prod_catg_id_fk_col")
	private Category category;
	
	
}

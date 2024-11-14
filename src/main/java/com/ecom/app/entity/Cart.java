package com.ecom.app.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "cart_table")
public class Cart {

	@Id
	@GeneratedValue
	@Column(name = "cart_id_col")
	private Integer id;
	
	
	@ManyToOne
	@JoinColumn(name = "cart_total_count")
	private Product product;
	
	
  @Column(name = "prod_qty_col")
  private Integer quantity;
	  
	 	
	@ManyToOne
	@JoinColumn(name = "user_id_col")
	private User user;
	/*
	 * @Column(name="prod_subTotal") private Double subTotal;
	 */	
	
	public Double getSubTotal() {
		return this.product.getDiscountedPrice()*quantity;
	}
	}

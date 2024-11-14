package com.ecom.app.entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "order_dtl_table")
public class OrderDetails {

	@Id
	@GeneratedValue
	@Column(name="order_id_col")
	private Integer id;
	
	@Column(name = "order_date")
	private Date orderDate;
	
	@Column(name="order_status")
	private String orderStatus;
	
	@Column(name="order_qty")
	private Integer orderQty;
	
	@Column(name = "user_address_col")
	private String address;
	
	@Column(name = "phone_col")
	private String phone;

	
	@Column(name = "product_name")
	private String orderName;	
	
	
	@Column(name = "total_price_col")
	private Double totalPrice;
	
	@Column(name="order_item_price")
	private Double unitPrice;
		
		
	@ManyToOne
	@JoinColumn(name = "user_id_col")
	private User user;
	
	
	@Column(name = "user_payment_type")
	private String paymentType;

	
}

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
@Table(name = "order_input_table")
public class OrderInput {

	
	@Id
	@GeneratedValue
	@Column(name="order_id_input_col")
	private Integer id;
	
	@Column(name = "order_new_date")
	private Date orderDate;
	
	@Column(name="order_new_status")
	private String orderStatus;
	
	@Column(name="order_new_qty")
	private Integer orderQty;
	
	@Column(name = "user_new_address_col")
	private String address;
	
	@Column(name = "order_phone_col")
	private String phone;

	
	@Column(name = "order_product_name")
	private String orderName;	
	
	
	@Column(name = "Order_total_price_col")
	private Double totalPrice;
	
	@Column(name="order_item_unit_price")
	private Double unitPrice;
		
		
	@ManyToOne
	@JoinColumn(name = "user_id_input_col")
	private User user;
	
	
	@Column(name = "user_new_payment_type")
	private String paymentType;

	

}

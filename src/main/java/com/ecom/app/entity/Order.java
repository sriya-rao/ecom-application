package com.ecom.app.entity;


import java.util.List;import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="user_order_table")
public class Order {

	
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Integer id;
	
	@Column(name = "user_first_name")
	private String orderDate;
	
	@Column(name = "user_last_name")
	private String deliveryDate;
	
	
	@Column(name = "user_address_col")
	private String address;
	
	@Column(name = "country_col")
	private String phone;
	
	 
	@Column(name = "order_price_col")
	private Double totalPrice;
	
	@Column(name = "user_payment_type")
	private String paymentType;
	
	
	
	@ManyToOne
	@JoinColumn(name = "User_order_col")
	private User user;
}
	
	
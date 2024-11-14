package com.ecom.app.entity;


import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="user_table")
public class User {

	@Id
	@GeneratedValue(generator = "user")
	@SequenceGenerator(name = "user" ,sequenceName = "user_seq")
	@Column(name = "user_id_col")
	private Integer id;
	
	@Column(name = "user_name_col")
	private String name;
	
	@Column(name = "user_mail_col")
	private String email;

	@Column(name = "user_pwd_col")
	private String pwd;

	@Column(name = "user_phone_col")
	private String phone;
	
	@Column(name = "user_addr_col")
	private String address;
	
	@Column(name = "user_pic_col")
	private String userPic;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "user_role_tab",
	joinColumns = @JoinColumn(name = "user_role_id_col"))
	private Set<String> roles;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "orders_list")
	private List<Order> orders;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id_col")
	private Cart cart;
	
	
}

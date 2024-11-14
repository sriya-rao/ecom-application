package com.ecom.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.app.entity.Order;
import com.ecom.app.entity.OrderDetails;
import com.ecom.app.repository.OrderDetailsRepository;
import com.ecom.app.repository.OrderRepository;


@Service
public class OrderServiceImpl {
	
	@Autowired
	OrderRepository repository;
	
	@Autowired
	OrderDetailsRepository orderDetailsRepository;

	public Boolean saveOrder(Order order) {
		repository.save(order);
		return true;
	}
	
	/*
	 * public List<Order> findOrderByUserId(String email){ return
	 * repository.findOrderByUserEmail(email); }
	 */
	public List<Order> getAllOrders(){
		return repository.findAll();
	}
	
	
	}

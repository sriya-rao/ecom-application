package com.ecom.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.app.entity.OrderDetails;
import com.ecom.app.repository.OrderDetailsRepository;

@Service
public class OrderdetailsService {
	
	@Autowired
	OrderDetailsRepository repository;

	public Boolean saveOrderDetails(OrderDetails details) {
		repository.save(details);
		return true;
	}
	
	
	   public List<OrderDetails> getOrderDetailsByUserName(String email){
		   return repository.getOrderDetailsByUserName(email);
	   }
}

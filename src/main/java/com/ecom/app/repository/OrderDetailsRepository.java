package com.ecom.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecom.app.entity.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer>{

	
	  @Query("SELECT details FROM OrderDetails details JOIN details.user as user WHERE user.email=:email")
	   public List<OrderDetails> getOrderDetailsByUserName(String email);
	 
}

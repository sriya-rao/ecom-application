package com.ecom.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecom.app.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	
	/*
	 * @Query("SELECT order FROM Order order JOIN order.user as user WHERE user.email=:email"
	 * ) public List<Order> findOrderByUserEmail(String email);
	 */	
}

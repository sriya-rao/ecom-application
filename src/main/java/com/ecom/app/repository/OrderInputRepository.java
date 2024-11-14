package com.ecom.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.app.entity.OrderInput;

public interface OrderInputRepository extends JpaRepository<OrderInput, Integer>{

}

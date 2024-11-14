package com.ecom.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecom.app.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Query("SELECT products FROM Product products JOIN products.category as category WHERE category.id=:id ")
	public List<Product> getProductsByCategory(Integer id);
}

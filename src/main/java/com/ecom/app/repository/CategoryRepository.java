package com.ecom.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecom.app.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	@Query("SELECT id,categoryName FROM Category")
	public List<Object[]> getIdAndName();
}

package com.ecom.app.service;

import java.util.List;
import java.util.Map;

import com.ecom.app.entity.Category;

public interface CategoryService {
 
	public Boolean saveCategory(Category category);
	
	public Map<Integer, String> getIdAndName();
	
	public List<Category> getAllCategories();

}

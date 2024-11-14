package com.ecom.app.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.app.entity.Category;
import com.ecom.app.repository.CategoryRepository;
import com.ecom.app.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepository repository;

	@Override
	public Boolean saveCategory(Category category) {
		// TODO Auto-generated method stub
		repository.save(category);
		return true;
	}

	@Override
	public Map<Integer, String> getIdAndName() {
       List<Object[]> list=repository.getIdAndName();
       Map<Integer, String> map=list.stream().collect(Collectors.toMap(ob->Integer.valueOf(ob[0].toString()), ob->ob[1].toString()));
		return map;
	}

	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

}

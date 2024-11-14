package com.ecom.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.app.entity.Product;
import com.ecom.app.repository.ProductRepository;
import com.ecom.app.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository repository;

	
	@Override
	public Boolean saveProduct(Product product) {
       repository.save(product);
		return true;
	}


	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}


	@Override
	public void deleteProduct(Integer id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}


	@Override
	public List<Product> getProductsByCategory(Integer id) {
		// TODO Auto-generated method stub
		return repository.getProductsByCategory(id);
	}

	@Override
	public Product getProductById(Integer prodId) {
		return repository.findById(prodId).get();
	}
}

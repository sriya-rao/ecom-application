package com.ecom.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.app.entity.Product;

@Service
public interface ProductService {

	public Boolean saveProduct(Product product);
	
	public List<Product> getAllProducts();
	
	public void deleteProduct(Integer id);
	
	public List<Product> getProductsByCategory(Integer id);
	
	public Product getProductById(Integer prodId);


}

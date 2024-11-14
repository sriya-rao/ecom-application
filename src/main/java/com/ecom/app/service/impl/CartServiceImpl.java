package com.ecom.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.app.entity.Cart;
import com.ecom.app.entity.Product;
import com.ecom.app.entity.User;
import com.ecom.app.repository.CartRepository;
import com.ecom.app.service.CartService;

import jakarta.transaction.Transactional;


@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	CartRepository repository;
	
	

	public Boolean saveCart(Cart cart) {
		repository.save(cart);
		return true;
	}
	
	public Integer countOfCartItemsByUserEmail(String email) {
		return repository.countOfCartItemsByUserEmail(email);
	}

	
		
	public List<Cart> getCartByUserEmail(String email){
	 return	repository.getCartByUserEmail(email);
		
	}
	
	public Cart findCartByProduct(Integer prodId,String email) {
		return repository.findCartByProductIdAndCustomer(prodId,email);
	}
	
	@Transactional
	public void updateQuantity(Product product,Integer qty,User user) {
		repository.updateQuantityOfCart(product, qty, user);
		
	}
	
	public void deleteCartByid(Integer id) {
		repository.deleteById(id);
	}
	
	@Transactional
	public void deleteCartByProductIdAndUser(Product product,String email) {
		repository.deleteCartByProductAndUserMail(product,email);
	}
	
	
}

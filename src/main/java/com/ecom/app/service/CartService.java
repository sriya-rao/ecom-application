package com.ecom.app.service;

import java.util.List;

import com.ecom.app.entity.Cart;
import com.ecom.app.entity.Product;
import com.ecom.app.entity.User;

public interface CartService {

	public Boolean saveCart(Cart cart);

    public Integer countOfCartItemsByUserEmail(String email);

	public List<Cart> getCartByUserEmail(String email);

				
  public Cart findCartByProduct(Integer prodId,String email);

  public void updateQuantity(Product product,Integer qty,User user);

						
	public void deleteCartByid(Integer id);
							
	public void deleteCartByProductIdAndUser(Product product,String email);


}

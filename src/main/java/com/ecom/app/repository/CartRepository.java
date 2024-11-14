package com.ecom.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ecom.app.entity.Cart;
import com.ecom.app.entity.Product;
import com.ecom.app.entity.User;

public interface CartRepository extends JpaRepository<Cart, Integer>{

	@Query("SELECT cart FROM Cart cart JOIN cart.user as user WHERE user.email=:email")
	public List<Cart> getCartByUserEmail(String email);
	
	@Query("SELECT COUNT(cart) FROM Cart cart WHERE cart.user.email=:email")
	public Integer countOfCartItemsByUserEmail(String email);
	
	@Modifying
	@Query("DELETE FROM Cart cart  WHERE cart.product=:product AND cart.user.email=:email")
	public void deleteCartByProductAndUserMail(Product product,String email);
	
	@Query("SELECT cart FROM Cart cart  WHERE cart.product.id=:prodId AND cart.user.email=:email")
	public Cart findCartByProductIdAndCustomer(Integer prodId,String email);
	
	@Modifying
	@Query("UPDATE Cart cart SET cart.quantity=:qty WHERE cart.product=:product AND cart.user=:user"
			)
	public void updateQuantityOfCart(Product product,Integer qty,User user);
}

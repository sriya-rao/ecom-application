package com.ecom.app.controller;


import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecom.app.entity.Cart;
import com.ecom.app.entity.OrderDetails;
import com.ecom.app.entity.Product;
import com.ecom.app.entity.User;
import com.ecom.app.service.CartService;
import com.ecom.app.service.ProductService;
import com.ecom.app.service.UserService;
import com.ecom.app.service.impl.CartServiceImpl;
import com.ecom.app.service.impl.ProductServiceImpl;
import com.ecom.app.service.impl.UserServiceImpl;
import com.ecom.app.util.UtilClass;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CartService cartService;

	
	
	
	@GetMapping("/add")
	public String addToCart(@RequestParam Integer prodId,Model model,Principal p)
	{
		Integer count=cartService.countOfCartItemsByUserEmail(p.getName());
 		model.addAttribute("count", count);
		Product product=productService.getProductById(prodId);
	    User user=	userService.findByEmail(p.getName()).get();
	
	   Cart cart=   cartService.findCartByProduct(prodId, p.getName());
	
	if(cart!=null) {
		Integer qty=cart.getQuantity();
		cart.setQuantity(qty+1);
		cartService.saveCart(cart);
	}
	
	if(cart==null) {
	
		cart=new Cart();
		cart.setProduct(product);
		cart.setUser(user);
	    cart.setQuantity(UtilClass.quantity);
		cartService.saveCart(cart);
		
	}
		
		return "redirect:../home/all";
	}
	
	
	
	 @GetMapping("/show")
	 public String showCart(Principal p,Model  model) {
		 Integer count=cartService.countOfCartItemsByUserEmail(p.getName());
	 		model.addAttribute("count", count);
		 CountAndPrice(model, p);
	  
	  return "CartPage"; 
	  }
	 
	 public void CountAndPrice(Model model,Principal p) {
			List<Cart> cart= cartService.getCartByUserEmail(p.getName());
			Double totalPrice=0.0;
			 Integer qty=0;
		       for(Cart c:cart) { 
		    	   qty=qty+c.getQuantity();
	          
		    	totalPrice+=c.getProduct().getDiscountedPrice()*c.getQuantity();
		    	}
		       model.addAttribute("cart", cart);
			    model.addAttribute("qty", qty);
			    model.addAttribute("totalPrice", totalPrice);      
		}

		
		
		
		  @PostMapping("/update")
		  public String cartUpdate(@RequestParam Integer prodId,@RequestParam Integer qty,Principal p,Model model) {
			 Product product= productService.getProductById(prodId);			User user=userService.findByEmail(p.getName()).get();
		  cartService.updateQuantity(product,qty, user); 
				 		  
		 return "redirect:show"; } 
		 
		  
		  
		
		
		 		
		  
		 	 
		 	
	@GetMapping("/remove")
	public String removeItemFromCart(@RequestParam Integer prodId,Principal p,Model model) {
		Product product=productService.getProductById(prodId);	
		//User user=userService.findByEmail(p.getName()).get();
	    cartService.deleteCartByProductIdAndUser(product,p.getName());
		return "redirect:show";
	}
	
	@GetMapping("/single")
	public String buySingleItem(@RequestParam Integer prodId,Principal p,Model model,HttpSession session) {
		Product product=productService.getProductById(prodId);
        session.setAttribute("product", product);
		model.addAttribute("id", prodId);
		OrderDetails details=new OrderDetails();
		model.addAttribute("details", details);
        
		return "Checkout";
		
	}
	
	@GetMapping("/checkout")
	public String checkOutPage(Model model,Principal p) {
		
        model.addAttribute("cart", cartService.getCartByUserEmail(p.getName()));
		model.addAttribute("id", 0);
		OrderDetails details=new OrderDetails();
       // order.setEmail(p.getName());
		model.addAttribute("details", details);
		return "Checkout";
	}
	
	
	
	
	
	
	
}

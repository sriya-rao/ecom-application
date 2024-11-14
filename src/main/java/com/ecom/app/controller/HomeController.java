package com.ecom.app.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecom.app.entity.Cart;
import com.ecom.app.entity.Category;
import com.ecom.app.entity.OrderDetails;
import com.ecom.app.entity.Product;
import com.ecom.app.entity.User;
import com.ecom.app.service.CartService;
import com.ecom.app.service.CategoryService;
import com.ecom.app.service.ProductService;
import com.ecom.app.service.UserService;
import com.ecom.app.service.impl.OrderServiceImpl;
import com.ecom.app.service.impl.OrderdetailsService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	ProductService service;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	OrderServiceImpl orderService;

	@Autowired
	CartService cartService;
	
	@Autowired
	OrderdetailsService orderdetailsService;
	
	@Autowired
	CartController controller;
	
	@GetMapping("/all")
	public String getAllProducts(Model model,Principal p,HttpSession session) {
		List<Product> list=service.getAllProducts();
		model.addAttribute("list", list);
        model.addAttribute("cart", new Cart());
	      controller.CountAndPrice(model, p);

		List<Category> catlist=categoryService.getAllCategories();
		model.addAttribute("catlist", catlist);
		return "Home";
	}
	
	@GetMapping("/single")
	public String showProduct(@RequestParam Integer id,Model model,Principal principal) {
	Product product=	service.getProductById(id);
    controller.CountAndPrice(model, principal);

		model.addAttribute("product", product);
		return "SingleProduct";
		
	}
	
	
	@GetMapping("/category")
	public String getProductsByCategoryId(@RequestParam Integer id,Model model,Principal p) {
		List<Product> list=service.getProductsByCategory(id);
		model.addAttribute("list", list);
		if(list.isEmpty()) {
			model.addAttribute("message", "No Item in this category");
		}
		List<Category> catlist=categoryService.getAllCategories();
		Integer count=cartService.countOfCartItemsByUserEmail(p.getName());
 		model.addAttribute("count", count);
		model.addAttribute("catlist", catlist);
 		model.addAttribute("cart",  new Cart());

		return "Home";
	}
	
	@GetMapping("/admin")
	public String loadAdminPage(Model model,Principal p) {
		List<Product> list=service.getAllProducts();
		List<Category> catlist=categoryService.getAllCategories();
		List<User> users=userService.getAllUsers();
		model.addAttribute("list", list);
		model.addAttribute("catlist", catlist);
 		model.addAttribute("cart",  new Cart());
 		Integer count=cartService.countOfCartItemsByUserEmail(p.getName());
 		model.addAttribute("count", count);

		model.addAttribute("users", users);
		return "Admin";
	}
	
	
	
	
	@PostMapping("/order/save")
	public String saveOrder(@ModelAttribute OrderDetails details,@RequestParam Integer prodId,Model model,Principal principal) {
		Integer count=cartService.countOfCartItemsByUserEmail(principal.getName());
 		model.addAttribute("count", count);
 		Boolean isSaved=false;
		
 		if(prodId==0) {
		List<Cart> cart= cartService.getCartByUserEmail(principal.getName());
		
		Double totalPrice=0.0;
		for(Cart c:cart) {
			totalPrice+=c.getProduct().getDiscountedPrice()*c.getQuantity();
		}
	
		for(Cart c:cart) {
			OrderDetails details2=new OrderDetails();
			details2.setOrderDate(new Date());
			details2.setOrderQty(c.getQuantity());
			details2.setOrderStatus("Ordered");
			details2.setOrderName(c.getProduct().getProdTitle());
			details2.setUser(c.getUser());
			details2.setUnitPrice(c.getProduct().getDiscountedPrice());	
			details2.setTotalPrice(c.getProduct().getDiscountedPrice()*c.getQuantity());
			
	        isSaved= orderdetailsService.saveOrderDetails(details2);
			
			cartService.deleteCartByProductIdAndUser(c.getProduct(), principal.getName());
		}
		
		
				
		
 		model.addAttribute("cart",  cart);
 		}
 		
 		
 		if(prodId!=0) {
 			Product product=service.getProductById(prodId);
 			User user=userService.findByEmail(principal.getName()).get();
 			OrderDetails details2=new OrderDetails();
 			details2.setOrderDate(new Date());
 			details2.setOrderQty(1);
 			details2.setOrderStatus("Ordered");
 			details2.setOrderName(product.getProdTitle());
 			details2.setUser(user);
 			details2.setUnitPrice(product.getDiscountedPrice());	
 			details2.setTotalPrice(product.getDiscountedPrice()*details2.getOrderQty());
 		    isSaved=orderdetailsService.saveOrderDetails(details2);
 			System.out.println("saved");
 			model.addAttribute("total", product.getDiscountedPrice()*details2.getOrderQty());

 		}
 		
 		if(isSaved) {
		
		return "redirect:../orderplace";
 		}else {
 			return "redirect:../all";
 		}
	}
	
	
	@GetMapping("/orderplace")
	public String orderPlaced()
	{
		return "OrderPlace";
	}
	
		

	
	@GetMapping("/order/details")
	public String orderPage(Model model,Principal p) {
	      controller.CountAndPrice(model, p);
				List<OrderDetails> details=orderdetailsService.getOrderDetailsByUserName(p.getName());
         model.addAttribute("list", details);
       //model.addAttribute("cart", cartService.getCartByUserEmail(p.getName()));
		return "OrderDetails";
	}
	
	
}

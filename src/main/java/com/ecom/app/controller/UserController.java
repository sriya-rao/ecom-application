package com.ecom.app.controller;


import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecom.app.entity.Cart;
import com.ecom.app.entity.User;
import com.ecom.app.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController {
            
	        @Autowired
	      private  UserService service;
	        
	     
	
	       @GetMapping("/register")
	       public String loadRegisterPage(Model model) {
	    	   model.addAttribute("user", new User());
	    	   return "Register";
	       }
	       
	       @GetMapping("/login")
	       public String loginPage(Model model) {

	    	   return "Login";
	       }
	       
	       
	       @PostMapping("/save")
	       public String saveUser(@ModelAttribute User user, Model model) {
	    	   user.setUserPic("default.png");
	    	  Set<String> roles =new HashSet<>();
	    	  roles.add("user");
	    	   user.setRoles(roles);
	    	   Boolean isSaved=service.saveUser(user);
	    	   if(isSaved) {
	    		   model.addAttribute("message", "Registered successfully Please Login");	
	    		   //emailUtil.sendEmail(user.getEmail(), "Hi "+user.getName(), "Registration Successfull");
	    	   }
	    	   else 
				model.addAttribute("message", "Unable to Register");
			    
	    	   
	    	   model.addAttribute("user", new User());
	    	   return "Register";
	       }
	
	       
	       
	       @GetMapping("/all")
	       public String getAllUsers(Model model) {
	    	   List<User> list=service.getAllUsers();
	    	   model.addAttribute("list", list);
	    	   return "UserData";
	       }
	       
	       @GetMapping("/setup")
	       public String loginSetUp(Principal p,HttpSession session,Model model) {
	    	Optional<User> user=  service.findByEmail(p.getName());
	    	  session.setAttribute("userOb", user.get());
	    	  
	    	 return   "redirect:../home/all";
	       }
	      
	       @GetMapping("/checkMail") 
	 	  public @ResponseBody String validateEmail(@RequestParam String mail) { 
	 		  String msg="";
	 	  if(service.getUserEmailCount(mail)) { 
	 		  msg=mail+" already exists";
	 		  } 
	 	  return msg;
	 	          
	 	  }	       
	       
}

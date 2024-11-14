package com.ecom.app;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ecom.app.entity.User;
import com.ecom.app.service.UserService;

@Component
public class SetUpAdmin implements CommandLineRunner{
	
	@Autowired
	UserService service;

	@Override
	public void run(String... args) throws Exception {
  
		
		  User user=new User(); user.setAddress("hyd");
		  user.setEmail("sriya@gmail.com"); user.setName("sriya");
		  user.setPhone("9875446555"); user.setPwd("sri");
		  user.setRoles(Set.of("admin","user")); user.setUserPic("default.png");
		  if(service.findByEmail(user.getEmail()).isEmpty()) {
			  service.saveUser(user);

		  }
		 	}

}

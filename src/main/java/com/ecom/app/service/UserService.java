package com.ecom.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecom.app.entity.User;

@Service
public interface UserService {

	public Boolean saveUser(User user);
	

	public List<User> getAllUsers();
	
	public Optional<User> findByEmail(String email);
	
	 Boolean getUserEmailCount(String email);

}

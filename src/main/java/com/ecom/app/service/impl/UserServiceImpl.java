package com.ecom.app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecom.app.entity.User;
import com.ecom.app.repository.UserRepository;
import com.ecom.app.service.UserService;

@Service
public class UserServiceImpl implements UserService,UserDetailsService{
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	 BCryptPasswordEncoder encoder;

	@Override
	public Boolean saveUser(User user) {
		//read pwd
		String pwd=user.getPwd();
		//encode pwd
	String encPwd =	encoder.encode(pwd);
	user.setPwd(encPwd);
		
       repository.save(user);
		return true;
	}

		@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
	
	
	public User getUserById(Integer userId) {
		return repository.findById(userId).get();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	Optional<User> optional=repository.findByEmail(username);
	
	if(optional.isEmpty()) {
		throw new UsernameNotFoundException("User not exists");
	}
	else {
		  User user=optional.get();
		return new org.springframework.security.core.userdetails.User(
				username, user.getPwd(), user.getRoles().stream().map(role->new SimpleGrantedAuthority(role))
				.collect(Collectors.toSet()));
	}

	}

	@Override
	public Optional<User> findByEmail(String email) {
		
		return repository.findByEmail(email);
	}

	@Override
	public Boolean getUserEmailCount(String email) {
		return repository.getUserEmailCount(email)>0;
	}

	
	
	
	
	

}

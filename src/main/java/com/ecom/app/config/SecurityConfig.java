package com.ecom.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	  @Bean
	  protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.authorizeHttpRequests((requests)->requests
	        .requestMatchers("/resources/**","/static/**","/js/**","/css/**","/images/**").permitAll()
	        .requestMatchers("/user/login","/user/**","/home/all","/user/checkMail","product/image").permitAll()
	        .requestMatchers("/category/load","/category/all","/product/load","/product/allFiles","/product/image","/product/viewAll","/user/**").hasAuthority("admin")
	        .requestMatchers("/cart/add","/user/checkMail").hasAnyAuthority("admin","user")
	        .anyRequest().authenticated())
	        .formLogin((form)->form.loginPage("/user/login").loginProcessingUrl("/login")
	        .defaultSuccessUrl("/user/setup", true).failureUrl("/user/login?error"))
	        .logout((logout)->logout.logoutRequestMatcher(new AntPathRequestMatcher("/home/logout"))
	        .logoutSuccessUrl("/user/login?success"));
	        		
	       
	        return http.build();
	    }
	  
	  
	  @Autowired
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    	auth.userDetailsService(userDetailsService)
	    	.passwordEncoder(passwordEncoder);
	  }
	  
	 
	
}

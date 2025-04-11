package com.banking.demo.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.banking.demo.models.User;
import com.banking.demo.repositories.UserRepository;
import com.banking.demo.services.AuthService;
import com.banking.demo.utils.JWTUtil;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private UserRepository userRepository;//injecting the user repository

	@Autowired
	private JWTUtil jwtUtil;//injecting JWT utility
	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;//injects password encoded
	
	
	@Override
	public User registerUser(String username, String password, String role) {
		
		User existingUser = userRepository.findByUsername(username);
		
		if(existingUser != null) {
			throw new RuntimeException("User already exist, try to login!");
		}
		
		User user = new User(username, passwordEncoder.encode(password), role);//encrypting password before saving to db
		//check for existing user and throw a correct message to the client
		
		System.out.println("user we r getting here : " + user.getUsername());
		return userRepository.save(user);//saving user to the database
	}

	@Override
	public String authenticateUser(String username, String password) {
		User existingUser = userRepository.findByUsername(username);
		
		
		if(existingUser == null || !passwordEncoder.matches(password, existingUser.getPassword())){
			throw new RuntimeException("Invalid username or password");
		}
		
		return jwtUtil.generateToken(username);//returning JWT token if authentication is successfull
		
		 
	}

}

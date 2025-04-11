package com.banking.demo.services;

import com.banking.demo.models.User;

public interface AuthService {
	
	//register user
	public User registerUser(String username, String password, String role);
	
	
	//login user
	public String authenticateUser(String username, String password);

}

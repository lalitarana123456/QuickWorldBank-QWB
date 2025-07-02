package com.banking.demo.controllersAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.demo.models.User;
import com.banking.demo.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUserHandler(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("role") String role){
		
		return new ResponseEntity<User> (authService.registerUser(username, password, role), HttpStatus.OK);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> authenticateUserHandler(@RequestParam("username") String username, @RequestParam("password") String password){
		
		return new ResponseEntity<String>(authService.authenticateUser(username, password), HttpStatus.OK);
		
	}
	
	

}

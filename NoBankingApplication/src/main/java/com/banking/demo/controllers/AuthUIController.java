package com.banking.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthUIController {
	
	
	@GetMapping("/signin")
	public String showLoginPage(){
		
		return "signin"; 
		
	}

}

package com.banking.demo.controllersMVC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.banking.demo.models.User;
import com.banking.demo.services.AuthService;

@Controller
public class AuthUILogicController {
	
	
	@Autowired
	private AuthService authService;
	
	
	@PostMapping("/signup")
	public String registerUser(@ModelAttribute("user") User user){
		
		authService.registerUser(user.getUsername(), user.getPassword(), user.getRole());
		return "redirect:/signin";
	}
	
	

}

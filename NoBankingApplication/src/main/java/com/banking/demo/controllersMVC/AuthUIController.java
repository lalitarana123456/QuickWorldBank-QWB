package com.banking.demo.controllersMVC;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.banking.demo.models.User;



@Controller
public class AuthUIController {
	
	
	@GetMapping("/signin")
	public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model){
		
		if(error !=null) {
			model.addAttribute("errorMsg", "Invalid username or password!");
		}
		return "signin"; 
		
	}
	
	@GetMapping("/signup")
	public String showRegisterPage(Model model){
		model.addAttribute("user", new User());//for form binding
		
		return "signup";
	}

}

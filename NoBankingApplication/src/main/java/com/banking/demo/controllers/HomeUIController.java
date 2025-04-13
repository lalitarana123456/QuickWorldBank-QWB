package com.banking.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeUIController {
	
	
	@GetMapping("/home")
	public String home(){
		return "/home";
	}

}

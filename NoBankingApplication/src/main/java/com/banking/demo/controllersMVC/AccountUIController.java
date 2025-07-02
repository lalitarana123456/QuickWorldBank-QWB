//package com.banking.demo.controllersMVC;
//
//import java.security.Principal;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import com.banking.demo.models.Account;
//import com.banking.demo.models.User;
//import com.banking.demo.repositories.UserRepository;
//import com.banking.demo.services.AccountService;
//
//@Controller
//public class AccountUIController {
//
//	@Autowired
//	private AccountService accountService;
//	
//	@Autowired
//	private UserRepository userRepository;
//	
//	
//	
//	@GetMapping("/account/create")
//	public String createAccountUI(Model model){
//		model.addAttribute("account", new Account());
//		
//		return "create_account";
//	}
//	
//	@PostMapping("account/create")
//	public String createAccount(@ModelAttribute Account account, Principal principal){
//		//setting loogged in user from the prinicipal 
//		User user =  userRepository.findByUsername(principal.getName());
//		account.setUser(user); 
//		accountService.createAccount(account);
//		
//		
//		return "redirect:/accounts";
//	
//	}
//	
//	@GetMapping("/accounts")
//	public String getUserAccounts(Model model){
//		
//		List<Account> accounts = accountService.getAllAccounts();
//		model.addAttribute("accounts", accounts);
//		
//		return "accounts";
//		
//	}
//}

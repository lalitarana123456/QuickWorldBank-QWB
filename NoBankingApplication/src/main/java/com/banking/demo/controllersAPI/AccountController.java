package com.banking.demo.controllersAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.demo.models.Account;
import com.banking.demo.services.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<Account>> getAllAccountsHandler(){
		return new ResponseEntity<List<Account>>(accountService.getAllAccounts(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/{id}")
	public ResponseEntity<Account> getAccountByIdHandler(@PathVariable Long Id){
		
		return new ResponseEntity<Account>(accountService.getAccountById(Id), HttpStatus.OK);
		
	}
	@PreAuthorize("hasRole('ADMIN', 'USER')")
	@PostMapping
	public ResponseEntity<Account> createAccountHandler(@RequestBody Account account){
		
		return new ResponseEntity<Account>(accountService.createAccount(account), HttpStatus.OK);
	    	
	}
	
	@PreAuthorize("hasRole('ADMIN', 'USER')")
	@PostMapping("/deposit")
	public ResponseEntity<Account> depositHandler(@RequestParam("accountNumber") String accountNumber, @RequestParam("amount") Double amount){
		
		return new ResponseEntity<Account>(accountService.deposit(accountNumber, amount), HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ADMIN', 'USER')")
	@PostMapping("/withdraw")
	public ResponseEntity<Account> withdrawHandler(@RequestParam("accountNumber") String accountNumber, @RequestParam("amount") Double amount){
		
		return new ResponseEntity<Account>(accountService.withdraw(accountNumber, amount), HttpStatus.OK);
	}
	
	

}

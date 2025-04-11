package com.banking.demo.services;

import java.util.List;

import com.banking.demo.models.Account;

public interface AccountService {
	
	public List<Account> getAllAccounts();
	
	public Account getAccountById(Long Id);
	
	public Account createAccount(Account account);

	public Account deposit(String accountNumber, Double balance);
	
	public Account withdraw(String accountNumber, Double amount);
	
	
	
}

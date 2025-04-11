package com.banking.demo.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.demo.models.Account;
import com.banking.demo.repositories.AccountRepository;
import com.banking.demo.services.AccountService;

@Service
public class AcountServiceImpl implements AccountService{

	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public List<Account> getAllAccounts() {
		
		return accountRepository.findAll();
	}

	@Override
	public Account getAccountById(Long Id) {
		//if account is not present then what will be happend
		
		return accountRepository.findById(Id).get();
	}

	@Override
	public Account createAccount(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public Account deposit(String accountNumber, Double balance) {
		Account existingAccount = accountRepository.findByAccountNumber(accountNumber);
		System.out.println("Account number u r trying to deposit amount " + existingAccount);
		if(existingAccount == null) {
			throw new RuntimeException("Account not found");
		}
		
		existingAccount.setBalance(existingAccount.getBalance() + balance);
		
		accountRepository.save(existingAccount);
		
		return existingAccount;
	}

	@Override
	public Account withdraw(String accountNumber, Double amount) {
		
		Account existingAccount = accountRepository.findByAccountNumber(accountNumber);
		
		if(existingAccount == null) {
			throw new RuntimeException("Account not found");
		}
		else if(existingAccount.getBalance()<amount) {
			throw new RuntimeException("Insufficient Balance");
		}
		
		existingAccount.setBalance(existingAccount.getBalance() - amount);
		
		accountRepository.save(existingAccount);
		
		return existingAccount;
	}

}

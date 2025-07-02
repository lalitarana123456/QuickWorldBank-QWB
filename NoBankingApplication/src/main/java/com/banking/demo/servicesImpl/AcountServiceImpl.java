package com.banking.demo.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.banking.demo.models.Account;
import com.banking.demo.models.User;
import com.banking.demo.repositories.AccountRepository;
import com.banking.demo.repositories.UserRepository;
import com.banking.demo.services.AccountService;

@Service
public class AcountServiceImpl implements AccountService{

	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<Account> getAllAccounts() {
		
		//extracting user name
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		User existingUser = userRepository.findByUsername(userName);
		
		return accountRepository.findByUser(existingUser);
	}

	@Override
	public Account getAccountById(Long Id) {
		//if account is not present then what will be happend
		
		return accountRepository.findById(Id).get();
	}

	@Override
	public Account createAccount(Account account) {
		System.out.println("Account holder name" + account.getAccountHolderName());
		//getting currently logged in user
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user =  userRepository.findByUsername(username);
		
		account.setUser(user);
		
		//generating unique 12 digits account number
		String generatedAccountNumber = generateUniqueAccountNumber();
		account.setAccountNumber(generatedAccountNumber);
		
		
		return accountRepository.save(account);
	}
	
	//method which will generate usinque account number\
	private String generateUniqueAccountNumber() {
		String accountNumber;
		
		do {
			accountNumber = String.valueOf((long)(Math.random() * 1_000_000_000_000L));
			
			while(accountNumber.length() < 12) {
				accountNumber = "0" + accountNumber;
			}
		}while(accountRepository.findByAccountNumber(accountNumber) !=null );
		
		return accountNumber;
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

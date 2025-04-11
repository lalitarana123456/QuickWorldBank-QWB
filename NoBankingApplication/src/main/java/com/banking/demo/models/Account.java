package com.banking.demo.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String accountNumber;
	private String accountHolderName;
	private Double balance;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	//const to intialise or dynamic data
	
	public Account() {
		
	}
	public Account(String accountNumber, String accountHolderName, Double balance) {
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.balance = balance;
		
	}
	
	
	//now getter setter 
	public Long getId() {
		return id;
	}
	
	public String getAccountNumber(){
		return accountNumber;
	}
	
	public String getAccountHolderName(){
		return accountHolderName;
	}
	
	public Double getBalance(){
		return balance;
	}
	
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	

}

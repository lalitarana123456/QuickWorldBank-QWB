package com.banking.demo.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String accountNumber;
	
	@NotNull
	private String accountHolderName;
	
	@NotNull
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
	
	public void setAccountNumber(String accountNumber){
		this.accountNumber = accountNumber;
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
	
	public void setUser(User user){
		this.user = user;
	}
}

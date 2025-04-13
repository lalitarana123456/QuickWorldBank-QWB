package com.banking.demo.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String username;
	
	
	private String password;//we will store encrypted password
	
	private String role;//role can be user or admin
	
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Account> accounts = new ArrayList<>();

	
	//constructor
	public User(){
		
	}
	
	
	public User(String username, String password, String role){
		
		this.username = username;
		this.password = password;
		this.role = role;
		 
	}
	
	//we will provide getter and setter as per the requirement
	public String getPassword(){
		return this.password;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getRole(){
		return role;
	} 
}

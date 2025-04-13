package com.banking.demo.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User implements UserDetails{
	
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
	
	public String getRole(){
		return role;
	}

	
	@Override
	public String getPassword(){
		return this.password;
	}
	
	
	@Override
	public String getUsername(){
		return username;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(() -> "ROLE_" + role);
	}
	
	
	@Override
	public boolean isAccountNonExpired(){
		return true;
		
	}
	
	
	@Override
	public boolean isAccountNonLocked(){
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired(){
		return true;
	}
	
	
	@Override
	public boolean isEnabled(){
		return true;
	}


}

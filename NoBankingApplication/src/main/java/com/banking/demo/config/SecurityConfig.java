package com.banking.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.banking.demo.servicesImpl.CustomUserDetailsService;


@Configuration //marking this class as a security configuration 
public class SecurityConfig {
	
	@Autowired
	private CustomUserDetailsService userDetailsServices;
	
	
	@Bean//what is the use of this method?????????????
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean//what is the use of this method?????????????
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception{
		return config.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http
		.csrf(csrf -> csrf.disable())//disable CSRF protection for postman testing
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/api/auth/**", "/signin", "/register", "/css/**", "/js/**").permitAll()
				.anyRequest().authenticated()
				)
		.formLogin(form -> form
				.loginPage("/signin")
				.loginProcessingUrl("/doLogin")
				.defaultSuccessUrl("/create_account", true)
				.permitAll()
				);
		//.logout(logout -> logout.permitAll()
	//			);
//		.sessionManagement(session -> session
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				);
		
		
		return http.build();
		
	}

}

package com.banking.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration //marking this class as a security configuration 
public class SecurityConfig {
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http
		.csrf(csrf -> csrf.disable())//disable CSRF protection for postman testing
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/signin", "/doLogin", "/api/auth/**", "/css/**", "/js/**").permitAll()
				.anyRequest().authenticated()
				)
		.formLogin(form -> form
				.loginPage("/signin")
				.loginProcessingUrl("/doLogin")
				.defaultSuccessUrl("/home", true)
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

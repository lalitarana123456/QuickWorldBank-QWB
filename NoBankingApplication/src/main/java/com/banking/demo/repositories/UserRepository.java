package com.banking.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.demo.models.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);//explicit method
	
}

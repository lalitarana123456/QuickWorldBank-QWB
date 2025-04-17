package com.banking.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.demo.models.Account;
import com.banking.demo.models.User;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	
	Account findByAccountNumber(String accountNumber);

	List<Account> findByUser(User user);//getting all accounts associated with the a particular user
}

package com.saib.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saib.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long>{

	  public List<Account> findByAccountType(String type);
	  public List<Account> findAccountByGender(String gender);
	 
}
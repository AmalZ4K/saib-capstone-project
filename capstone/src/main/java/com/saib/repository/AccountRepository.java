package com.saib.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.saib.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long>{
	@Query(value = "SELECT m FROM Account m WHERE m.accountType = :type")
	public List<Account> findByType(String type);

}
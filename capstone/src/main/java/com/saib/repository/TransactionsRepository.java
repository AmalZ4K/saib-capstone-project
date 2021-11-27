package com.saib.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saib.models.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions,Long> {
	
	List<Transactions> findByTransactionType(String transactionType);
}
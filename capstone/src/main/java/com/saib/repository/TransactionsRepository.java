package com.saib.repository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saib.models.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions,Long> {
	
	List<Transactions> findTransactionByTransactionType(String transactionType);
	List<Transactions> findTransactionByDate(LocalDateTime date);
	List<Transactions> findTransactionByDateAndTransactionType(LocalDateTime date,String transactionType);
}
package com.saib.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.saib.models.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions,Long> {
	@Query(value = "SELECT m FROM Transactions m WHERE m.TransactionsType = :type")
	public Optional<Transactions> findByType(String transaction_type);
}

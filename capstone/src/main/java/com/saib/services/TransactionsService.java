package com.saib.services;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import com.saib.models.Transactions;
import com.saib.repository.TransactionsRepository;
import com.saib.util.Results;

@Service
public class TransactionsService {
	@Autowired
	TransactionsRepository transactionsRepository;
	
	

	public List<Transactions> getAllTransactions()
	{
		List<Transactions> list=transactionsRepository.findAll();
		return list;
	}
		
	
	public Transactions getTransactionByTransactionNumber(long transactionNumber)
	{
		Optional<Transactions> optional=transactionsRepository.findById(transactionNumber);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction with Transaction Number:"+transactionNumber+"doesn't exist");
		}
		
	}
	
	public List<Transactions> getTransactionsByType(String type)
	{
		List<Transactions> list=transactionsRepository.findByTransactionType(type);
		if(!list.isEmpty())
			return list;
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction With type " + type + " does not exits");
		}
		
		
	}
	
	public String addTransaction(Transactions transaction)
	{
		String result="";
		Transactions storedTransaction=transactionsRepository.save(transaction);
		if(storedTransaction!=null) {
			result=Results.SUCCESS;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction not created");
		}
		
		return result;
	}
	
	public String updateTransaction(Transactions transaction, long transactionNumber)
	{
		String result="";
		
		transaction.setTransactionId(transactionNumber);
		Transactions updatedTransaction=transactionsRepository.save(transaction);
		
		if(updatedTransaction!=null)
		{
			result=Results.SUCCESS;
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record was not updated");
		}
		return result;
		
	}
	
	public String deleteTransaction(long transactionNumber)
	{
		String result="";
		try {
		transactionsRepository.deleteById(transactionNumber);
		
		
			result=Results.SUCCESS;
			return result;
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}

}

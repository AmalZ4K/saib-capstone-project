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
	
	public Transactions getTransactionsbyID(long transaction_id)
	{
		Optional<Transactions> optional=transactionsRepository.findById(transaction_id);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transactions with this Id Number:"+transaction_id+"doesn't exist");
		}
		
	}
	
	public List<Transactions> getTransactionsByType(String transaction_type)
	{
		List<Transactions> list=transactionsRepository.findByType(transaction_type);
		
		if(!list.isEmpty())
			return list;
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction With type " + transaction_type + " does not exits");
		}
		
	}
	
	
	public String addTransaction(Transactions transactions)
	{
		String result="";
		Transactions storedTransactions=transactionsRepository.save(transactions);
		if(storedTransactions!=null) {
			result=Results.SUCCESS;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction has not been sent!");
		}
		
		return result;
	}
	
	public String updateTransactions(Transactions transactions, long transaction_id)
	{
		String result="";
		
		transactions.setTransaction_id(transaction_id);
		Transactions updatedTransactions=transactionsRepository.save(transactions);
		
		if(updatedTransactions!=null)
		{
			result=Results.SUCCESS;
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record was not updated");
		}
		return result;
		
	}
	
	public String deleteTransactions(long transaction_id)
	{
		String result="";
		try {
			transactionsRepository.deleteById(transaction_id);
		
		
			result=Results.SUCCESS;
			return result;
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
		
	}

}

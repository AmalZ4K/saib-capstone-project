package com.saib.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.saib.config.ApiSucessPayload;
import com.saib.models.Transactions;
import com.saib.services.TransactionsService;
import com.saib.util.Results;

@RestController
public class TransactionsController {
	
	@Autowired
	TransactionsService transactionsService;
	
	
	@GetMapping("/transactions")
	public ResponseEntity<ApiSucessPayload> getAllTransactions()
	{
		List<Transactions> list=transactionsService.getAllTransactions();
		
		ApiSucessPayload payload=ApiSucessPayload.build(list, "Transactions Fetched", HttpStatus.OK);
		ResponseEntity<ApiSucessPayload> response=new ResponseEntity<ApiSucessPayload>(payload,HttpStatus.OK);
		
		return response;
		
	}
	
	
	@GetMapping("/transactions/{transaction_id}")
	public ResponseEntity<ApiSucessPayload> getTransactionsbyID(@PathVariable long transaction_id) 
	{
		Transactions transactions=transactionsService.getTransactionsbyID(transaction_id); 
		
		ApiSucessPayload payload=ApiSucessPayload.build(transactions, "Success",HttpStatus.OK);
		ResponseEntity<ApiSucessPayload> response=new ResponseEntity<ApiSucessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	
	@GetMapping("/transactions/{transaction_type}")
	public ResponseEntity<ApiSucessPayload> getTransactionsByType(@PathVariable String transaction_type) 
	{
        List<Transactions> list = transactionsService.getTransactionsByType(transaction_type);
		
		ApiSucessPayload payload=ApiSucessPayload.build(list, "Success",HttpStatus.OK);
		ResponseEntity<ApiSucessPayload> response=new ResponseEntity<ApiSucessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	@PostMapping("/transactions")
	public ResponseEntity<ApiSucessPayload> addTransaction(@RequestBody Transactions transactions) 
	{
		ResponseEntity<ApiSucessPayload> response=null;
		System.out.println(transactions);
		String result=transactionsService.addTransaction(transactions);
		if(result.equalsIgnoreCase(Results.SUCCESS))
		{
			ApiSucessPayload payload=ApiSucessPayload.build(result, "The Transaction has been done successfully", HttpStatus.CREATED);
			response=new ResponseEntity<ApiSucessPayload>(payload,HttpStatus.CREATED);
		}
		
		return response;
	
	}
	
	@PutMapping("/transactions/{transaction_id}")
	public ResponseEntity<ApiSucessPayload> updateTransactions(@RequestBody Transactions transactions, @PathVariable long transaction_id)
	{
		String result=transactionsService.updateTransactions(transactions, transaction_id); 
		ApiSucessPayload payload=ApiSucessPayload.build(result,result,HttpStatus.OK);
		ResponseEntity<ApiSucessPayload> response=new ResponseEntity<ApiSucessPayload>(payload, HttpStatus.OK);
		return response;
	}
	
	
	@DeleteMapping("/transactions/{transaction_id}")
	public ResponseEntity<ApiSucessPayload> deleteTransactions(@PathVariable long transaction_id) 
	{
		String result=transactionsService.deleteTransactions(transaction_id); 
		ApiSucessPayload payload=ApiSucessPayload.build(result,result,HttpStatus.OK);
		ResponseEntity<ApiSucessPayload> response=new ResponseEntity<ApiSucessPayload>(payload, HttpStatus.OK);
		return response;
	}

}

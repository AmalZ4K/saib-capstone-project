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
	
	@GetMapping("/transaction")
	public ResponseEntity<ApiSucessPayload> getAllTransactions()
	{
		List<Transactions> list=transactionsService.getAllTransactions();
		
		ApiSucessPayload payload=ApiSucessPayload.build(list, "Transactions Fetched", HttpStatus.OK);
		ResponseEntity<ApiSucessPayload> response=new ResponseEntity<ApiSucessPayload>(payload,HttpStatus.OK);
		
		return response;
		
	}
	
	@GetMapping("/transaction/{transactionId}")
	public ResponseEntity<ApiSucessPayload> getTransactionByTransactionNumber(@PathVariable long transactionId)
	{
		Transactions transaction=transactionsService.getTransactionByTransactionNumber(transactionId);
		
		ApiSucessPayload payload=ApiSucessPayload.build(transaction, "Success",HttpStatus.OK);
		ResponseEntity<ApiSucessPayload> response=new ResponseEntity<ApiSucessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/transaction/type/{type}")
	public ResponseEntity<ApiSucessPayload> getTransactionByType(@PathVariable String type)
	{
		List<Transactions> list = transactionsService.getTransactionsByType(type);
		
		ApiSucessPayload payload=ApiSucessPayload.build(list, "Success",HttpStatus.OK);
		ResponseEntity<ApiSucessPayload> response=new ResponseEntity<ApiSucessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	@PostMapping("/transaction")
	public ResponseEntity<ApiSucessPayload> addTransaction(@RequestBody Transactions transaction)
	{
		ResponseEntity<ApiSucessPayload> response=null;
		System.out.println(transaction);
		String result=transactionsService.addTransaction(transaction);
		if(result.equalsIgnoreCase(Results.SUCCESS))
		{
			ApiSucessPayload payload=ApiSucessPayload.build(result, "Transaction created successfully", HttpStatus.CREATED);
			response=new ResponseEntity<ApiSucessPayload>(payload,HttpStatus.CREATED);
			
		}
		return response;
		
	}
	

	@PutMapping("/transaction/{transactionId}")
	public ResponseEntity<ApiSucessPayload> updateTransaction(@RequestBody Transactions transaction, @PathVariable long transactionId)
	{
		String result=transactionsService.updateTransaction(transaction, transactionId);
		ApiSucessPayload payload=ApiSucessPayload.build(result,result,HttpStatus.OK);
		ResponseEntity<ApiSucessPayload> response=new ResponseEntity<ApiSucessPayload>(payload, HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("/transaction/{transactionId}")
	public ResponseEntity<ApiSucessPayload> deleteTransaction(@PathVariable long transactionId)
	{
		String result=transactionsService.deleteTransaction(transactionId);
		ApiSucessPayload payload=ApiSucessPayload.build(result,result,HttpStatus.OK);
		ResponseEntity<ApiSucessPayload> response=new ResponseEntity<ApiSucessPayload>(payload, HttpStatus.OK);
		return response;
	}

}

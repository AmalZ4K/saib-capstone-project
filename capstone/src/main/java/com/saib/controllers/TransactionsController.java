
package com.saib.controllers;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saib.config.ApiSucessPayload;
import com.saib.models.Account;
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
	
	@GetMapping("/transactions/transactionType/{transactionType}")
	public ResponseEntity<ApiSucessPayload> getTransactionByTransactionType(@PathVariable String transactionType)
	{
		List<Transactions> list = transactionsService.getTransactionByTransactionType(transactionType);
		
		ApiSucessPayload payload=ApiSucessPayload.build(list, "Success",HttpStatus.OK);
		ResponseEntity<ApiSucessPayload> response=new ResponseEntity<ApiSucessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/transactions/date/{date}")
	public ResponseEntity<ApiSucessPayload> getTransactionByDate(@RequestParam @DateTimeFormat (pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime date)
	{
		List<Transactions> list = transactionsService.getTransactionByDate(date);
		
		ApiSucessPayload payload=ApiSucessPayload.build(list, "Success",HttpStatus.OK);
		ResponseEntity<ApiSucessPayload> response=new ResponseEntity<ApiSucessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/transactions/{transactionType}/{date}")
	public ResponseEntity<ApiSucessPayload> getTransactionByDateAndType(@RequestParam @DateTimeFormat (pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime date,
			@PathVariable String transactionType)
	{
		List<Transactions> list = transactionsService.getTransactionByDateAndType(date,transactionType);
		
		ApiSucessPayload payload=ApiSucessPayload.build(list, "Success",HttpStatus.OK);
		ResponseEntity<ApiSucessPayload> response=new ResponseEntity<ApiSucessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	
	
	 @GetMapping("/transactions/all")
	public ResponseEntity<ApiSucessPayload> getAllTransactions(@RequestParam int pageNumber, @RequestParam int pageSize)
	{
		List<Transactions> list = transactionsService.getAllTransactions(pageNumber, pageSize);
		HttpStatus status=HttpStatus.OK;
		ApiSucessPayload payload=ApiSucessPayload.build(list, "Accounts Found",status);
		ResponseEntity<ApiSucessPayload> response=new ResponseEntity<ApiSucessPayload>(payload, status);
		return response;
	}
		@GetMapping("/transactions/all/sorted")
		public ResponseEntity<ApiSucessPayload> getAllTransactions(@RequestParam int pageNumber, @RequestParam int pageSize, @RequestParam String sortBy)
		{
			List<Transactions> list = transactionsService.getAllTransactions(pageNumber, pageSize,sortBy);
			HttpStatus status=HttpStatus.OK;
			ApiSucessPayload payload=ApiSucessPayload.build(list, "Accounts Found",status);
			ResponseEntity<ApiSucessPayload> response=new ResponseEntity<ApiSucessPayload>(payload, status);
			return response;
		}
	
	@PostMapping("/transactions")
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

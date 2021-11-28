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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.saib.config.ApiSucessPayload;
import com.saib.models.Account;
import com.saib.services.AccountService;
import com.saib.util.Results;

@RestController
public class AccountController {
	/*
	 *  GET - /accounts - Get me all details 
	 *  GET - /accounts/id - Get me details for a single account 
	 *  POST - /accounts - Creating a new account 
	 *  PUT - /accounts/id - Updating an existing account 
	 *  DELETE -/accounts/id - for deleting an account from db
	 *  
	 *  
	 */
	@Autowired
	AccountService accountService;
	
	
	@GetMapping("/accounts")
	public ResponseEntity<ApiSucessPayload> getAllAccounts()
	{
		List<Account> list=accountService.getAllAccount();
		
		ApiSucessPayload payload=ApiSucessPayload.build(list, "Accounts Fetched", HttpStatus.OK);
		ResponseEntity<ApiSucessPayload> response=new ResponseEntity<ApiSucessPayload>(payload,HttpStatus.OK);
		
		return response;
		
	}
	
	@GetMapping("/accounts/{accountNumber}")
	public ResponseEntity<ApiSucessPayload> getAccountbyAccountNumber(@PathVariable long accountNumber)
	{
		Account account=accountService.getAccountByAccountNumber(accountNumber);
		
		ApiSucessPayload payload=ApiSucessPayload.build(account, "Success",HttpStatus.OK);
		ResponseEntity<ApiSucessPayload> response=new ResponseEntity<ApiSucessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/accounts/accountType/{type}")
	public ResponseEntity<ApiSucessPayload> getAccountsByType(@PathVariable String type)
	{
		List<Account> list =accountService.getAccountsByType(type);
		
		ApiSucessPayload payload=ApiSucessPayload.build(list, "Success",HttpStatus.OK);
		ResponseEntity<ApiSucessPayload> response=new ResponseEntity<ApiSucessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	
	
	@GetMapping("/accounts/gender/{gender}")
	public ResponseEntity<ApiSucessPayload> getAccountByGender(@PathVariable String gender)
	{
		List<Account> list=accountService.getAccountsByGender(gender);
		HttpStatus status=HttpStatus.OK;
		ApiSucessPayload payload=ApiSucessPayload.build(list, "Accounts Found",status);
		ResponseEntity<ApiSucessPayload> response=new ResponseEntity<ApiSucessPayload>(payload, status);
		return response;
		
	}
	
	 /**@GetMapping("/accounts/all")
	public ResponseEntity<ApiSucessPayload> getAllAccounts(@RequestParam int pageNumber, @RequestParam int pageSize)
	{
		List<Account> list = accountService.getAllAccount(pageNumber, pageSize);
		HttpStatus status=HttpStatus.OK;
		ApiSucessPayload payload=ApiSucessPayload.build(list, "Accounts Found",status);
		ResponseEntity<ApiSucessPayload> response=new ResponseEntity<ApiSucessPayload>(payload, status);
		return response;
	} */
	
	@GetMapping("/accounts/all/sorted")
	public ResponseEntity<ApiSucessPayload> getAllAccounts(@RequestParam int pageNumber, @RequestParam int pageSize, @RequestParam String sortBy)
	{
		List<Account> list = accountService.getAllAccount(pageNumber, pageSize,sortBy);
		HttpStatus status=HttpStatus.OK;
		ApiSucessPayload payload=ApiSucessPayload.build(list, "Accounts Found",status);
		ResponseEntity<ApiSucessPayload> response=new ResponseEntity<ApiSucessPayload>(payload, status);
		return response;
	}
	
	
	@PostMapping("/accounts")
	public ResponseEntity<ApiSucessPayload> addAccount(@RequestBody Account account)
	{
		ResponseEntity<ApiSucessPayload> response=null;
		System.out.println(account);
		String result=accountService.addAccount(account);
		if(result.equalsIgnoreCase(Results.SUCCESS))
		{
			ApiSucessPayload payload=ApiSucessPayload.build(result, "Account created successfully", HttpStatus.CREATED);
			response=new ResponseEntity<ApiSucessPayload>(payload,HttpStatus.CREATED);
		}
		
		return response;
	
	}
	
	@PutMapping("/accounts/{accountNumber}")
	public ResponseEntity<ApiSucessPayload> updateAccount(@RequestBody Account account, @PathVariable long accountNumber)
	{
		String result=accountService.updateAccount(account, accountNumber);
		ApiSucessPayload payload=ApiSucessPayload.build(result,result,HttpStatus.OK);
		ResponseEntity<ApiSucessPayload> response=new ResponseEntity<ApiSucessPayload>(payload, HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("/accounts/{accountNumber}")
	public ResponseEntity<ApiSucessPayload> deleteAccount(@PathVariable long accountNumber)
	{
		String result=accountService.deleteAccount(accountNumber);
		ApiSucessPayload payload=ApiSucessPayload.build(result,result,HttpStatus.OK);
		ResponseEntity<ApiSucessPayload> response=new ResponseEntity<ApiSucessPayload>(payload, HttpStatus.OK);
		return response;
	}
	
	
	
	

}
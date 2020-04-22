package com.cg.onlinewallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlinewallet.entities.WalletUser;
import com.cg.onlinewallet.service.*;

@RestController
public class OnlineWalletController {
    
	@Autowired
	private OnlineWalletService service;
	
	public OnlineWalletController() {
		// TODO Auto-generated constructor stub
	}
	@RequestMapping("/")
	public String check()
	{
		return "WORKING";
	}
	
	@PostMapping("/register")
	public ResponseEntity<Integer> regsiterUser(@RequestBody WalletUser user)
	{   
		Integer userId=service.resgisterUser(user);
		return new ResponseEntity<Integer>(userId,HttpStatus.OK);
		
	}
	@PutMapping("/addmoney/{userId}")
	public ResponseEntity<String> addMoney(@PathVariable("userId") Integer userId, @RequestBody Double amount)
	{
		service.addMoney(userId, amount);
		return new ResponseEntity<String>("the money is added in the account succesfully",HttpStatus.OK);
	}
	@GetMapping("/showbalance/{userId}")
	public ResponseEntity<Double> showBalance(@PathVariable("userId") Integer userId)
	{
		Double balance=service.showBalance(userId);
		return new ResponseEntity<Double>(balance,HttpStatus.OK);
	}

}

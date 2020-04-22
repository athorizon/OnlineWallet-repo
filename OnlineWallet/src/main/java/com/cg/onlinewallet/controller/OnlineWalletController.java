package com.cg.onlinewallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	@RequestMapping("/check")
	public ResponseEntity<String> show()
	{
		return new ResponseEntity<String>("hello",HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Integer> regsiterUser(@RequestBody WalletUser user)
	{   
		Integer userId=service.resgisterUser(user);
		return new ResponseEntity<Integer>(userId,HttpStatus.OK);
		
	}
	@PutMapping("/addmoney")
	public ResponseEntity<String> addMoney(@RequestBody Integer userId, @RequestBody Double amount)
	{
		service.addMoney(userId, amount);
		return new ResponseEntity<String>("the money is added in the account succesfully",HttpStatus.OK);
	}

}

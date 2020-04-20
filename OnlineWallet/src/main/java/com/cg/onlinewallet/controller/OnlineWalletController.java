package com.cg.onlinewallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlinewallet.entities.WalletUser;
import com.cg.onlinewallet.service.*;

@RestController
public class OnlineWalletController {
    
	@Autowired
	OnlineWalletService service;
	public OnlineWalletController() {
		// TODO Auto-generated constructor stub
	}
	/*@RequestMapping("/check")
	public ResponseEntity<String> show()
	{
		return new ResponseEntity<String>("hello",HttpStatus.OK);
	}*/
	
	@PostMapping("/register")
	public ResponseEntity<String> saveUser(@RequestBody WalletUser user)
	{   
		service.resgisterUser(user);
		return new ResponseEntity<String>("User succesfully resgistered",HttpStatus.OK);
		
	}

}

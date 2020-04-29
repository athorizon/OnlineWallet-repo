package com.cg.onlinewallet.controller;

import java.util.List;

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
	public String check() {
		return "WORKING";
	}

	@PostMapping("/register")
	public ResponseEntity<String> regsiterUser(@RequestBody WalletUser user) {
		service.registerUser(user);
		return new ResponseEntity<String>("The User is Registered successfully", HttpStatus.OK);

	}

	@PutMapping("/addmoney/{userId}")
	public ResponseEntity<String> addMoney(@PathVariable("userId") Integer userId, Double amount) {
		service.addMoney(userId, amount);
		return new ResponseEntity<String>("the money is added in the account succesfully", HttpStatus.OK);
	}

	@GetMapping("/showbalance/{userId}")
	public ResponseEntity<Double> showBalance(@PathVariable("userId") Integer userId) {
		Double balance = service.showBalance(userId);
		return new ResponseEntity<Double>(balance, HttpStatus.OK);
	}

	@PutMapping("/transactmoney/{userId}")
	public ResponseEntity<String> transactMoney(@PathVariable("userId") Integer userId, Double amount,
			String loginName) {
		service.transactMoney(userId, loginName, amount);
		return new ResponseEntity<String>("Transaction Completed", HttpStatus.OK);
	}

	@GetMapping("/login")
	public ResponseEntity<Integer> login(String loginName, String password) {
		Integer userId = service.login(loginName, password);
		return new ResponseEntity<Integer>(userId, HttpStatus.OK);
	}
    
	@GetMapping("/admin")
	public ResponseEntity<Integer> loginAdmin(String loginName, String password) {
		Integer userId = service.loginAdmin(loginName, password);
		return new ResponseEntity<Integer>(userId, HttpStatus.OK);
	}

	@GetMapping("/admin/{adminId}/userlist")
	public ResponseEntity<List<String>> getUserList(@PathVariable("adminId") Integer userId, String userStatus) {
		List<String> userList = service.getUserList(userId, userStatus);
		return new ResponseEntity<List<String>>(userList, HttpStatus.OK);
	}

	@PutMapping("/admin/{adminId}/changestatus")
	public ResponseEntity<String> changeUserStatus(@PathVariable("adminId") Integer adminId, String loginName,
			String userStatus) {
		service.changeUserStatus(adminId, loginName, userStatus);
		return new ResponseEntity<String>("The User Status Changed Successfully", HttpStatus.OK);
	}
}

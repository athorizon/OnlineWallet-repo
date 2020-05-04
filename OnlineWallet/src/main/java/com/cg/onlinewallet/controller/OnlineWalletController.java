package com.cg.onlinewallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlinewallet.entities.WalletUser;
import com.cg.onlinewallet.service.*;
@CrossOrigin(origins="*")
@RestController
public class OnlineWalletController {

	@Autowired
	private OnlineWalletService onlineWalletService;

	public OnlineWalletController() {
		// TODO Auto-generated constructor stub
	}
    
	@RequestMapping("/")
	public String check() {
		return "WORKING";
	}
    
	@PostMapping("/register")
	public ResponseEntity<String> regsiterUser(@RequestBody WalletUser user) {
		String loginName=onlineWalletService.registerUser(user);
		return new ResponseEntity<String>(loginName, HttpStatus.OK);

	}

	@PutMapping("/addmoney/{userId}")
	public ResponseEntity<Double> addMoney(@PathVariable("userId") Integer userId, Double amount) {
		Double balance=onlineWalletService.addMoney(userId, amount);
		return new ResponseEntity<Double>(balance, HttpStatus.OK);
	}

	@GetMapping("/showbalance/{userId}")
	public ResponseEntity<Double> showBalance(@PathVariable("userId") Integer userId) {
		Double balance = onlineWalletService.showBalance(userId);
		return new ResponseEntity<Double>(balance, HttpStatus.OK);
	}

	@GetMapping("/transactmoney/{userId}")
	public ResponseEntity<String> transactMoney(@PathVariable("userId") Integer userId, Double amount,
			String email) {
		System.out.println("email: "+email);
		System.out.println("amount: "+amount);
		onlineWalletService.transactMoney(userId, email, amount);
		return new ResponseEntity<String>("Transaction Completed", HttpStatus.OK);
	}

	@GetMapping("/login")
	public ResponseEntity<Integer> login(String email, String password) {
		Integer userId = onlineWalletService.login(email, password);
		return new ResponseEntity<Integer>(userId, HttpStatus.OK);
	}
    
	@GetMapping("/admin")
	public ResponseEntity<Integer> loginAdmin(String email, String password) {
		Integer userId = onlineWalletService.loginAdmin(email, password);
		return new ResponseEntity<Integer>(userId, HttpStatus.OK);
	}

	@GetMapping("/admin/{adminId}/userlist")
	public ResponseEntity<List<String>> getUserList(@PathVariable("adminId") Integer userId, String userStatus) {
		List<String> userList = onlineWalletService.getUserList(userId, userStatus);
		return new ResponseEntity<List<String>>(userList, HttpStatus.OK);
	}

	@PutMapping("/admin/{adminId}/changestatus")
	public ResponseEntity<String> changeUserStatus(@PathVariable("adminId") Integer adminId, String email,
			String userStatus) {
		String returnLoginName=onlineWalletService.changeUserStatus(adminId, email, userStatus);
		return new ResponseEntity<String>(returnLoginName, HttpStatus.OK);
	}
}
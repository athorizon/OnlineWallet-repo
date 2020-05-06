/*********************************************************************************************************************************************************************************************
* @author:Arushi Bhardwaj
* Description: It is a controller class which will handle the web requests made by the user and then maps it according to the required handler method.
**********************************************************************************************************************************************************************************************/
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
import com.cg.onlinewallet.exceptions.UnauthorizedAccessException;
import com.cg.onlinewallet.exceptions.ValidationException;
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
/*********************************************************************************************************************
* Method:registerUser
* Description:To map the request of user for registering a new user
* @param user:User's Details
* @returns Entity:After registering the user it will return the email of the user for further use.
* Created By-Arushi Bhardwaj
***********************************************************************************************************************/
    @PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody WalletUser user) {
		String email=onlineWalletService.registerUser(user);
		return new ResponseEntity<String>(email, HttpStatus.OK);

	}
	
/*********************************************************************************************************************
* Method:addMoney
* Description:To map the request of user for adding money
* @param userId:User's Id
* @param amount:amount to be added
* @returns Entity:After adding money to the account it will return the amount added into the account.
* Created By-Arushi Bhardwaj
***********************************************************************************************************************/
	@PutMapping("/addmoney/{userId}")
	public ResponseEntity<Double> addMoney(@PathVariable("userId") Integer userId, Double amount) {
		Double balance=onlineWalletService.addMoney(userId, amount);
		return new ResponseEntity<Double>(balance, HttpStatus.OK);
	}

/*********************************************************************************************************************
* Method:showBalance
* Description:To map the request of user for showing the balance available in the account
* @param userId:User's Id
* @returns Entity: the balance that is available in the account.
* Created By-Kunal Maheshwari
***********************************************************************************************************************/
    @GetMapping("/showbalance/{userId}")
	public ResponseEntity<Double> showBalance(@PathVariable("userId") Integer userId) {
		Double balance = onlineWalletService.showBalance(userId);
		return new ResponseEntity<Double>(balance, HttpStatus.OK);
	}

/*********************************************************************************************************************
* Method:transactMoney
* Description:To map the request of user for transferring the amount from one user to another user account
* @param userId:User's Id
* @param amount:amount to be transferred
* @returns Entity:After transferring,it will give the message that transaction is completed
* Created By-Kunal Maheshwari
***********************************************************************************************************************/
	@GetMapping("/transactmoney/{userId}")
	public ResponseEntity<String> transactMoney(@PathVariable("userId") Integer userId, Double amount,
			String email) {
		System.out.println("email: "+email);
		System.out.println("amount: "+amount);
		onlineWalletService.transactMoney(userId, email, amount);
		return new ResponseEntity<String>("Transaction Completed", HttpStatus.OK);
	}

/*********************************************************************************************************************
* Method:login
* Description:To map the request of user for login into the application
* @param email:User's email
* @param password:User's password
* @returns Entity:After login,it will return the userId for the developer use only.
* Created By-Arushi Bhardwaj
***********************************************************************************************************************/
	@GetMapping("/login")
	public ResponseEntity<Integer> login(String email, String password) {
		Integer userId = onlineWalletService.login(email, password);
		return new ResponseEntity<Integer>(userId, HttpStatus.OK);
	}
	
/*********************************************************************************************************************
* Method:loginAdmin
* Description:To map the request of admin to login into the application
* @param email:Admin's email
* @param password:Admin's password
* @returns Entity:After login,it will return the userId for the developer use only.
* Created By-Arushi Bhardwaj
	***********************************************************************************************************************/
	@GetMapping("/admin")
	public ResponseEntity<Integer> loginAdmin(String email, String password) {
		Integer userId = onlineWalletService.loginAdmin(email, password);
		return new ResponseEntity<Integer>(userId, HttpStatus.OK);
	}

/*********************************************************************************************************************
* Method:getUserList
* Description:To map the request of admin for accessing the list of users who are either active or inactive users
* @param userId:User's Id
* @param userStatus:User's account status
* @returns Entity:it will return the list of users having active or inactive accounts
* Created By-Kunal Maheshwari
***********************************************************************************************************************/
	@GetMapping("/admin/{adminId}/userlist")
	public ResponseEntity<List<String>> getUserList(@PathVariable("adminId") Integer userId, String userStatus) {
		List<String> userList = onlineWalletService.getUserList(userId, userStatus);
		return new ResponseEntity<List<String>>(userList, HttpStatus.OK);
	}

/*********************************************************************************************************************
* Method:changeUserStatus
* Description:To map the request of admin to change the status of user's account to active or inactive
* @param userId:Admin's Id
* @param email:User's email
* @param userStatus: User's account status
* @returns Entity:it will change the user's account status and return that information
* Created By-Kunal Maheshwari
***********************************************************************************************************************/
	@GetMapping("/admin/{adminId}/changestatus")
	public ResponseEntity<String> changeUserStatus(@PathVariable("adminId") Integer adminId, String email,
			String userStatus) {
		String returnEmail=onlineWalletService.changeUserStatus(adminId, email, userStatus);
		return new ResponseEntity<String>(returnEmail, HttpStatus.OK);
	}
}
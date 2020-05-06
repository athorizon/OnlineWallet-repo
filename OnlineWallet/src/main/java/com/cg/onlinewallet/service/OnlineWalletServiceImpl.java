/*********************************************************************************************************************************************************************************************
* @author:Arushi Bhardwaj
* Description: It is a service class which is providing the functionality like login as a user or an admin,registering a new user and creating account for him,adding money to the account,
* checking balance available in the account and transferring the amount from one user account to another user account.
**********************************************************************************************************************************************************************************************/

package com.cg.onlinewallet.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.onlinewallet.dao.*;
import com.cg.onlinewallet.entities.*;
import com.cg.onlinewallet.entities.WalletAccount.status;
import com.cg.onlinewallet.entities.WalletUser.type;
import com.cg.onlinewallet.exceptions.*;

@Transactional
@Service
public class OnlineWalletServiceImpl implements OnlineWalletService {

	public OnlineWalletServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private OnlineWalletDao onlineWalletDao;

/*********************************************************************************************************************
* Method: login 
* Description: To Validate the user data so that the user can login
* @param email: User's email
* @param password:User's password
* @returns Integer: userId associated with the loginName provided if no exceptions occurs
* @throws UnauthorizedAccessException:it is raised if the account associated with email is not an active user
* @throws InvalidException:it is raised if the account associated with email is a admin type account
* @throws ValidationException:it is raised if the password dosen't matches with the user's stored password 
* Created By - Arushi Bhardwaj
***********************************************************************************************************************/

	@Override
	public Integer login(String email, String password) {
		if(!onlineWalletDao.checkUserByEmail(email))
			throw new UnauthorizedAccessException("User doesn't exist with this email.Kindly Register First");
		WalletUser user = onlineWalletDao.getUserByEmail(email);
		WalletAccount account = user.getAccountDetail();
		if (account.getUserStatus() == status.non_active)
			throw new UnauthorizedAccessException("Your Account is not Activated");
		if (user.getUserType() == type.admin)
			throw new InvalidException("You are not authorized to login from here");
		if (user.getPassword().equals(password) == false)
			throw new ValidationException("The email and password combination does not match");
		return user.getUserID();
	}

/*********************************************************************************************************************
* Method: loginAdmin 
* Description: To Validate the Admin data so that the Admin can login
* @param email:Admin's Email
* @param password: Admin's password
* @returns Integer: userId associated with the loginName provided if no exceptions occurs
* @throws UnauthorizedAccessException:it is raised if the account associated with email is not an admin type
* @throws ValidationException: it is raised if the password dosen't matches with the user's stored password 
* Created By -Arushi Bhardwaj
***********************************************************************************************************************/
	@Override
	public Integer loginAdmin(String email, String password) {
		if(!onlineWalletDao.checkUserByEmail(email))
			throw new UnauthorizedAccessException("You are not an Admin.Please login as User");
		WalletUser user = onlineWalletDao.getUserByEmail(email);
		if (user.getUserType() == type.user)
			throw new UnauthorizedAccessException("You are not authorized to login from here");
		if (user.getPassword().equals(password) == false)
			throw new ValidationException("The email and password Combination does not match");
		return user.getUserID();
	}

/*********************************************************************************************************************
* Method: getUserList 
* Description: To return the list of emails of the user according to user status provided.
* @param adminId:Admin's userId
* @param userstatus: user status
* @returns List<String>: List containing the emails of the user based on their userStatus either active or non_active.
* @throws UnauthorizedAccessException:it is raised if the account associated with adminId is not an admin type
* @throws WrongValueException:it is raised if the variable userStatus is other then values active and non_active 
* Created By - Kunal Maheshwari
***********************************************************************************************************************/
	@Override
	public List<String> getUserList(Integer adminId, String userStatus) {

		WalletUser admin = onlineWalletDao.getUser(adminId);
		if (admin.getUserType() == type.user)
			throw new UnauthorizedAccessException("You are not authorized to perform this task");
		if (userStatus.equalsIgnoreCase(new String("non_active")))
			return onlineWalletDao.getNonActiveUserList();
		else if (userStatus.equalsIgnoreCase(new String("active")))
			return onlineWalletDao.getActiveUserList();
		throw new WrongValueException("not a criteria to fetch user details");
	}

/*******************************************************************************************************************************
* Method: changeUserStatus 
* Description: Changes the status of account of user from active to non-active and other-way around
* @param adminId:Admin's userId
* @param email:User's email whose status has to be changed
* @param userstatus:user status
* @throws UnauthorizedAccessException:if the user associated with adminId is not a admin type
* @throws InvalidException:it is raised if there is no user associated with the email provided
* @throws UnauthorizedAccessException: it is raised if the user associated with the email is admin type
* @throws WrongValueException:it is raised if the variable userStatus is other then values active and non_active 
* Created By - Kunal Maheshwari
********************************************************************************************************************************/
	@Override
	public String changeUserStatus(Integer adminId, String email, String userStatus) {

		WalletUser admin = onlineWalletDao.getUser(adminId);
		if (admin.getUserType() == type.user)
			throw new UnauthorizedAccessException("You are Authorized to perform this task");
		if (onlineWalletDao.checkUserByEmail(email) == false)
			throw new InvalidException("There is no user with this email. Please Enter a valid email");
		WalletUser user = onlineWalletDao.getUserByEmail(email);
		if (user.getUserType() == type.admin)
			throw new UnauthorizedAccessException("Can't perform Task, Unauthorized Access");
		if (userStatus.equals(new String("non_active")))
			user.getAccountDetail().setUserStatus(status.non_active);
		else if (userStatus.equals(new String("active"))) {
			user.getAccountDetail().setUserStatus(status.active);
		} else
			throw new WrongValueException("The Status code does not exist");
		return user.getEmail();
	}

/*********************************************************************************************************************
* Method: registerUser 
* Description: Registers a new user into the wallet and also creates a account for user
* @param user:object containing data about user
@throws UnauthorizedAccessException: it is raised if the email provided with the user is already present 
Created By - Arushi Bhardwaj
***********************************************************************************************************************/

	@Override
	public String registerUser(WalletUser user) {
		// TODO Auto-generated method stub
		if (onlineWalletDao.checkUserByEmail(user.getEmail()) == true)
			throw new UnauthorizedAccessException("A User already exist with same email address");
		WalletAccount account = new WalletAccount(0.00, null, status.non_active);
		user.setAccountDetail(account);
		onlineWalletDao.saveAccount(account);
		onlineWalletDao.saveUser(user);
		return user.getEmail();
	}

/*********************************************************************************************************************
* Method: addMoney 
* Description: Increments the balance of the user balance with the amount provided
* @param userId:User's userId
* @param amount:amount which has to be incremented 
* Created By - Arushi Bhardwaj
***********************************************************************************************************************/
	@Override
	public Double addMoney(Integer userId, Double Amount) {
		WalletUser user = onlineWalletDao.getUser(userId);
		Integer accountId = user.getAccountDetail().getAccountID();
		WalletAccount account = onlineWalletDao.getAccount(accountId);
		Double balance = account.getAccountBalance();
		balance += Amount;
		account.setAccountBalance(balance);
		return account.getAccountBalance();
	}

/*********************************************************************************************************************
* Method: showBalance 
* Description: fetches and returns the balance of the user
* @param userId: User's userid
* @returns Double: Balance fetched from the user account 
* Created By - Kunal Maheshwari
***********************************************************************************************************************/
	@Override
	public Double showBalance(Integer userId) {
		WalletUser user = onlineWalletDao.getUser(userId);
		WalletAccount account = user.getAccountDetail();
		return account.getAccountBalance();
	}

/*********************************************************************************************************************
* Method: transactMoney 
* Description: Changes the status of account of user from active to non-active and other-way around
* @param userId:user's userId
* @param beneficiaryEmail:beneficiary's email to transfer money
* @param amount:amount to be transfered
* @throws InvalidException: it is raised if the beneficiary dosen't exist
* @throws InvalidException:it is raised if the beneficiary is not an active user
* @throws WrongValueException:it is raised if the amount is greater then the account balance of the user 
* Created By - Kunal Maheshwari
***********************************************************************************************************************/
	@Override
	public void transactMoney(Integer userId, String beneficiaryEmail, Double amount) {
		if (onlineWalletDao.checkUserByEmail(beneficiaryEmail) == false)
			throw new InvalidException("The Beneficary doesn't exist");
		WalletUser beneficiary = onlineWalletDao.getUserByEmail(beneficiaryEmail);
		if (beneficiary.getAccountDetail().getUserStatus() == status.non_active)
			throw new InvalidException("The Beneficiary must be an active user");
		WalletUser user = onlineWalletDao.getUser(userId);
		if (user.getAccountDetail().getAccountBalance() < amount)
			throw new WrongValueException("The Amount cannot be greater then available Balance");
		Integer beneficiaryId = beneficiary.getUserID();
		Double beneficiaryBalance = beneficiary.getAccountDetail().getAccountBalance();
		beneficiary.getAccountDetail().setAccountBalance(beneficiaryBalance + amount);
		Double userBalance = user.getAccountDetail().getAccountBalance();
		user.getAccountDetail().setAccountBalance(userBalance - amount);
		createTransaction(userId, "Amount has been tranfered. Balance has been updated", amount);
		createTransaction(beneficiaryId, "Amount credited to your account. Balance has been updated", amount);
	}
	
/*****************************************************************************************************************************
* Method: createTransaction 
* Description: it will give the overall description of the transaction made by the user like date,time,amount transferred etc.
* @param userId:user's userId
* @param description: description about the transaction made
* @param amount:amount to be transferred 
* Created By - Kunal Maheshwari
******************************************************************************************************************************/

	public void createTransaction(Integer userId, String description, Double amount) {
		WalletUser user = onlineWalletDao.getUser(userId);
		WalletAccount account = user.getAccountDetail();
		Double balance = account.getAccountBalance();
		WalletTransactions transaction = new WalletTransactions(description, LocalDateTime.now(), amount, balance);
		List<WalletTransactions> transactionList = account.getTransactionList();
		if (transactionList == null)
			transactionList = new ArrayList<WalletTransactions>();
		transactionList.add(transaction);
		onlineWalletDao.saveTransaction(transaction);
	}
}
package com.cg.onlinewallet.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinewallet.dao.*;
import com.cg.onlinewallet.entities.*;
import com.cg.onlinewallet.entities.WalletAccount.status;
import com.cg.onlinewallet.entities.WalletUser.type;
import com.cg.onlinewallet.entities.WalletUser.login;
import com.cg.onlinewallet.exceptions.*;

@Transactional
@Service
public class OnlineWalletServiceImp implements OnlineWalletService {

	public OnlineWalletServiceImp() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private OnlineWalletDao dao;

	@Override
	public Integer login(String loginName, String password) {
		if (dao.checkUserByLoginName(loginName) == false)
			throw new WrongValueException("The entered Login Name does not exist, Please enter a valid loginName");
		WalletUser user = dao.getUserByLoginName(loginName);
		if (user.getUserType() == type.admin)
			throw new InvalidException("You are not authorized to login from here");
		if (user.getPassword().equals(password) == false)
			throw new ValidationException("The LoginName and password Combination does not match");
		user.setLoginStatus(login.loggedIn);
		return user.getUserID();

	}

	private boolean checkLoginStatus(Integer userId) { // returns false if the user is not loggedin
		WalletUser user = dao.getUser(userId);
		if (user.getLoginStatus() == login.LoggedOut)
			return false;
		return true;
	}

	@Override
	public void logout(Integer userId) {
		WalletUser user = dao.getUser(userId);
		user.setLoginStatus(login.LoggedOut);
	}

	@Override
	public Integer loginAdmin(String loginName, String password) {
		if (dao.checkUserByLoginName(loginName) == false)
			throw new WrongValueException("The entered Login Name does not exist, Please enter a valid loginName");
		WalletUser user = dao.getUserByLoginName(loginName);
		if (user.getUserType() == type.user)
			throw new InvalidException("You are not authorized to login from here");
		if (user.getPassword().equals(password) == false)
			throw new ValidationException("The LoginName and password Combination does not match");
		user.setLoginStatus(login.loggedIn);
		return user.getUserID();
	}

	@Override
	public List<String> getUserList(Integer adminId, String userStatus) {
		if (checkLoginStatus(adminId) == false)
			throw new UnauthorizedAccessException("You must be loggedIn into the system to perform the task");
		WalletUser admin = dao.getUser(adminId);
		if (admin.getUserType() == type.user)
			throw new InvalidException("You are not authorized to perform this task");
		if (userStatus.equalsIgnoreCase(new String("non_active")))
			return dao.getNonActiveUserList();
		else if (userStatus.equalsIgnoreCase(new String("active")))
			return dao.getActiveUserList();
		throw new WrongValueException("not a criteria to fetch user details");
	}

	@Override
	public void changeUserStatus(Integer adminId, String loginName, String userStatus) {
		if (checkLoginStatus(adminId) == false)
			throw new UnauthorizedAccessException("You must be loggedIn into the system to perform the task");
		WalletUser admin = dao.getUser(adminId);
		if (admin.getUserType() == type.user)
			throw new UnauthorizedAccessException("You are Authorized to perform this task");
		if (dao.checkUserByLoginName(loginName) == false)
			throw new WrongValueException("The User does not exist");
		WalletUser user = dao.getUserByLoginName(loginName);
		if (user.getUserType() == type.admin)
			throw new UnauthorizedAccessException("Can't perform Task, Unauthorized Access");
		if (userStatus.equals(new String("non_active")))
			changeStatusNonActive(user.getUserID());
		else if (userStatus.equals(new String("active"))) {
			changeStatusActive(user.getUserID());
		} else
			throw new WrongValueException("The Status code does not exist");
	}

	private void changeStatusActive(Integer userId) {
		WalletUser user = dao.getUser(userId);
		WalletAccount account = user.getAccountDetail();
		if (account.getUserStatus() == status.active)
			throw new WrongValueException("User Already an active user");
		account.setUserStatus(status.active);
	}

	private void changeStatusNonActive(Integer userId) {
		WalletUser user = dao.getUser(userId);
		WalletAccount account = user.getAccountDetail();
		if (account.getUserStatus() == status.non_active)
			throw new WrongValueException("User Already a non_active user");
		account.setUserStatus(status.non_active);
	}

	@Override
	public void resgisterUser(WalletUser user) {
		// TODO Auto-generated method stub
		checkLoginName(user.getLoginName());
		WalletAccount account = new WalletAccount(0.00, null, status.non_active);
		dao.saveAccount(account);
		user.setAccountDetail(account);
		dao.saveUser(user);
	}

	@Override
	public void addMoney(Integer userId, Double Amount) {
		if (checkLoginStatus(userId) == false)
			throw new UnauthorizedAccessException("You must be loggedIn into the system to perform the task");
		WalletUser user = dao.getUser(userId);
		Integer accountId = user.getAccountDetail().getAccountID();
		WalletAccount account = dao.getAccount(accountId);
		Double balance = account.getAccountBalance();
		balance += Amount;
		account.setAccountBalance(balance);

	}

	@Override
	public Double showBalance(Integer userId) {
		if (checkLoginStatus(userId) == false)
			throw new UnauthorizedAccessException("You must be loggedIn into the system to perform the task");
		WalletUser user = dao.getUser(userId);
		WalletAccount account = user.getAccountDetail();
		return account.getAccountBalance();
	}

	@Override
	public void transactMoney(Integer userId, String beneficiaryLoginName, Double amount) {
		if (checkLoginStatus(userId) == false)
			throw new UnauthorizedAccessException("You must be loggedIn into the system to perform the task");
		checkBeneficiary(beneficiaryLoginName);
		checkBalanceLimit(userId, amount);
		WalletUser beneficiary = dao.getUserByLoginName(beneficiaryLoginName);
		if (beneficiary.getAccountDetail().getUserStatus() == status.non_active)
			throw new UnauthorizedAccessException("The Beneficiary must be an active user");
		Integer beneficiaryId = beneficiary.getUserID();
		addAmount(beneficiaryId, amount);
		deductAmount(userId, amount);
		createTransaction(userId, "Amount has been tranfered. Balance has been updated", amount);
		createTransaction(beneficiaryId, "Amount credited to your account. Balance has been updated", amount);
	}

	private void createTransaction(Integer userId, String description, Double amount) {
		WalletUser user = dao.getUser(userId);
		WalletAccount account = user.getAccountDetail();
		Double balance = account.getAccountBalance();
		WalletTransactions transaction = new WalletTransactions(description, LocalDateTime.now(), amount, balance);
		List<WalletTransactions> transactionList = account.getTransactionList();
		if (transactionList == null)
			transactionList = new ArrayList<WalletTransactions>();
		transactionList.add(transaction);
		dao.saveTransaction(transaction);
	}

	private void deductAmount(Integer userId, Double amount) {
		WalletUser user = dao.getUser(userId);
		WalletAccount account = user.getAccountDetail();
		Double balance = account.getAccountBalance();
		balance -= amount;
		account.setAccountBalance(balance);

	}

	private void addAmount(Integer userId, Double amount) {
		WalletUser user = dao.getUser(userId);
		WalletAccount account = user.getAccountDetail();
		Double balance = account.getAccountBalance();
		balance += amount;
		account.setAccountBalance(balance);

	}

	private boolean checkLoginName(String loginName) {
		if (dao.checkUserByLoginName(loginName) == true)
			throw new WrongValueException("Entered Login Name is already present, please enter another login Name");
		else
			return true;
	}

	private boolean checkBeneficiary(String beneficiaryLoginName) {
		boolean check = dao.checkUserByLoginName(beneficiaryLoginName);
		if (check == false)
			throw new InvalidException("The Beneficiary does not exist, please enter a valid LoginName");
		else
			return true;
	}

	private boolean checkBalanceLimit(Integer userId, Double amount) {
		WalletUser user = dao.getUser(userId);
		WalletAccount account = user.getAccountDetail();
		Double balance = account.getAccountBalance();
		if (amount >= balance)
			throw new WrongValueException("the amount entered cannot be greater or equal to account balance");
		return true;
	}
}

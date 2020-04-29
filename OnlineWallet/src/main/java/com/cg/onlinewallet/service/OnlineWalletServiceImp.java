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
public class OnlineWalletServiceImp implements OnlineWalletService {

	public OnlineWalletServiceImp() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private OnlineWalletDao dao;

	@Override
	public Integer login(String loginName, String password) {
		WalletUser user = dao.getUserByLoginName(loginName);
		WalletAccount account = user.getAccountDetail();
		if (account.getUserStatus() == status.non_active)
			throw new UnauthorizedAccessException("Your Account is not Activated");
		if (user.getUserType() == type.admin)
			throw new InvalidException("You are not authorized to login from here");
		if (user.getPassword().equals(password) == false)
			throw new ValidationException("The LoginName and password Combination does not match");
		return user.getUserID();
	}

	@Override
	public Integer loginAdmin(String loginName, String password) {
		WalletUser user = dao.getUserByLoginName(loginName);
		if (user.getUserType() == type.user)
			throw new UnauthorizedAccessException("You are not authorized to login from here");
		if (user.getPassword().equals(password) == false)
			throw new ValidationException("The LoginName and password Combination does not match");
		return user.getUserID();
	}

	@Override
	public List<String> getUserList(Integer adminId, String userStatus) {

		WalletUser admin = dao.getUser(adminId);
		if (admin.getUserType() == type.user)
			throw new UnauthorizedAccessException("You are not authorized to perform this task");
		if (userStatus.equalsIgnoreCase(new String("non_active")))
			return dao.getNonActiveUserList();
		else if (userStatus.equalsIgnoreCase(new String("active")))
			return dao.getActiveUserList();
		throw new WrongValueException("not a criteria to fetch user details");
	}

	@Override
	public void changeUserStatus(Integer adminId, String loginName, String userStatus) {

		WalletUser admin = dao.getUser(adminId);
		if (admin.getUserType() == type.user)
			throw new UnauthorizedAccessException("You are Authorized to perform this task");
		if (dao.checkUserByLoginName(loginName) == false)
			throw new InvalidException("There is no user with this LoginName. Please Enter a valid LoginName");
		WalletUser user = dao.getUserByLoginName(loginName);
		if (user.getUserType() == type.admin)
			throw new UnauthorizedAccessException("Can't perform Task, Unauthorized Access");
		if (userStatus.equals(new String("non_active")))
			user.getAccountDetail().setUserStatus(status.non_active);
		else if (userStatus.equals(new String("active"))) {
			user.getAccountDetail().setUserStatus(status.active);
		} else
			throw new WrongValueException("The Status code does not exist");
	}

	@Override
	public void registerUser(WalletUser user) {
		// TODO Auto-generated method stub
		if (dao.checkUserByLoginName(user.getLoginName()) == true)
			throw new UnauthorizedAccessException("The LoginName already exist");
		WalletAccount account = new WalletAccount(0.00, null, status.non_active);
		user.setAccountDetail(account);
		dao.saveAccount(account);
		dao.saveUser(user);
	}

	@Override
	public void addMoney(Integer userId, Double Amount) {
		WalletUser user = dao.getUser(userId);
		Integer accountId = user.getAccountDetail().getAccountID();
		WalletAccount account = dao.getAccount(accountId);
		Double balance = account.getAccountBalance();
		balance += Amount;
		account.setAccountBalance(balance);

	}

	@Override
	public Double showBalance(Integer userId) {
		WalletUser user = dao.getUser(userId);
		WalletAccount account = user.getAccountDetail();
		return account.getAccountBalance();
	}

	@Override
	public void transactMoney(Integer userId, String beneficiaryLoginName, Double amount) {
		if (dao.checkUserByLoginName(beneficiaryLoginName) == false)
			throw new InvalidException("The Beneficary doesn't exist");
		WalletUser beneficiary = dao.getUserByLoginName(beneficiaryLoginName);
		if (beneficiary.getAccountDetail().getUserStatus() == status.non_active)
			throw new UnauthorizedAccessException("The Beneficiary must be an active user");
		WalletUser user = dao.getUser(userId);
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

	public void createTransaction(Integer userId, String description, Double amount) {
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
}

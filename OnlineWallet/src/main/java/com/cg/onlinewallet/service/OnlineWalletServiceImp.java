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
	public Integer resgisterUser(WalletUser user) {
		// TODO Auto-generated method stub
		checkLoginName(user.getLoginName());
		WalletAccount account=new WalletAccount(0.00,null);
	    dao.persistAccount(account);
	    user.setAccountDetail(account);
		Integer userId=dao.persistUser(user);
		return userId;
	}
	
	@Override
	public void addMoney(Integer userId, Double Amount)
	{
	   WalletUser user=dao.getUser(userId);
	   Integer accountId=user.getAccountDetail().getAccountID();
	   WalletAccount account=dao.getAccount(accountId);
	   Double balance=account.getAccountBalance();
	   balance+=Amount;
	   account.setAccountBalance(balance);
	   dao.flush();
	}
	
	@Override
	public Double showBalance(Integer userId)
	{
		WalletUser user=dao.getUser(userId);
		WalletAccount account=user.getAccountDetail();
		return account.getAccountBalance();	
	}
	@Override
	public void transactMoney(Integer userId, String beneficiaryLoginName,Double amount)
	{
		checkBeneficiary(beneficiaryLoginName);
		checkBalanceLimit(userId,amount);
	    WalletUser beneficiary=dao.getUserByLoginName(beneficiaryLoginName);
	    Integer beneficiaryId=beneficiary.getUserID();
	    addAmount(beneficiaryId,amount);
	    deductAmount(userId,amount);
	    createTransaction(userId,"Amount has been tranfered. Balance has been updated",amount);
	    createTransaction(beneficiaryId,"Amount credited to your account. Balance has been updated",amount);
	}
	
	private void createTransaction(Integer userId,String description,Double amount)
	{   WalletUser user=dao.getUser(userId);
	    WalletAccount account=user.getAccountDetail();
		Double balance=account.getAccountBalance();
		WalletTransactions transaction=new WalletTransactions(description,LocalDateTime.now(),amount,balance);
		List<WalletTransactions> transactionList=account.getTransactionList();
		if(transactionList==null)
			transactionList=new ArrayList<WalletTransactions>();
		transactionList.add(transaction);
		dao.persistTransaction(transaction);
	}
	private void deductAmount(Integer userId,Double amount)
	{
		WalletUser user=dao.getUser(userId);
		WalletAccount account=user.getAccountDetail();
		Double balance=account.getAccountBalance();
		balance-=amount;
		account.setAccountBalance(balance);
		dao.flush();
	}
	private void addAmount(Integer userId,Double amount)
	{
		WalletUser user=dao.getUser(userId);
		WalletAccount account=user.getAccountDetail();
		Double balance=account.getAccountBalance();
		balance+=amount;
		account.setAccountBalance(balance);
		dao.flush();
	}
	boolean checkLoginName(String loginName) {
		 if(dao.checkUserByLoginName(loginName)==true)
			 throw new WrongValueException("Entered Login Name is already present, please enter another login Name");
		else 
			return true;
	}
	
	boolean checkBeneficiary(String beneficiaryLoginName)
	{
		boolean check=dao.checkUserByLoginName(beneficiaryLoginName);
	    if(check==false)
			throw new InvalidException("The Beneficiary does not exist, please enter a valid LoginName");
		else
			return true;
	}
	
	boolean checkBalanceLimit(Integer userId,Double amount)
	{
		WalletUser user=dao.getUser(userId);
		WalletAccount account=user.getAccountDetail();
		Double balance=account.getAccountBalance();
		if(amount>=balance)
			throw new WrongValueException("the amount entered cannot be greater or equal to account balance");
		return true;
	}
}

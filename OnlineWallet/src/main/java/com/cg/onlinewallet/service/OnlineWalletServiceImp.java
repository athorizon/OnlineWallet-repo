package com.cg.onlinewallet.service;

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
		/*checkLoginName(user.getLoginName());*/
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
	boolean checkLoginName(String loginName) {
		 if(dao.getLoginNameCount(loginName)!=null)
			 throw new WrongValueException("Entered Login Name is already present, please enter another login Name");
		else return true;
	}

}

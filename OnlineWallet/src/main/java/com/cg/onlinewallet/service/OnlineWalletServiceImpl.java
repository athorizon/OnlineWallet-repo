/****************************************************************************************************************************************************************************************************************************************************
 * @author Kunal Maheshwari
 * Description: It is a service class that provides various functionalities like registering a new user,adding money to the account,showing the available balance in the account and transferring the money from one to another user's account.
 * Created Date:
 ****************************************************************************************************************************************************************************************************************************************************/
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
    
/**********************************************************************************************************************************************
* Method:registerUser
* Description:It is used for registering a new user to use the online wallet.
* returns Integer: it will return a userId to be used by admin for further functionalities.
* Created By:Arushi Bhardwaj
* Created on:
***********************************************************************************************************************************************/
    
	@Override
	public Integer registerUser(WalletUser user) {
		// TODO Auto-generated method stub
		checkLoginName(user.getLoginName());
		WalletAccount account=new WalletAccount(0.00,null);
	    dao.persistAccount(account);
	    user.setAccountDetail(account);
		Integer userId=dao.persistUser(user);
		return userId;
	}
	
/**********************************************************************************************************************************************
* Method:addMoney
* Description:It is used for adding money to the account by the user.
* @param userId: User's ID to get the account details.
* @param amount: Amount to be added by the user into the acccount.
* Created By:Arushi Bhardwaj
* Created on:
***********************************************************************************************************************************************/
	
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
	
/**********************************************************************************************************************************************
* Method:showBalance
* Description:It is used for checking the available amount in the user's account.
* @param userId: User's ID to get the account details like account balance.
* returns Double: It will return the balance which is available in the user's account.
* Created By:Kunal Maheshwari
* Created on:
************************************************************************************************************************************************/
	
	@Override
	public Double showBalance(Integer userId)
	{
		WalletUser user=dao.getUser(userId);
		WalletAccount account=user.getAccountDetail();
		return account.getAccountBalance();	
	}
	
/**********************************************************************************************************************************************
* Method:checkLoginName
* Description:It is used for checking the loginname entered by the user that it is already present or not.
* @param loginName: User's Loginname
* returns Boolean: true, if the loginname is already present otherwise throws WrongValueException.
* @throws WrongValueException:It is raised as if loginname entered by user is already present in the database.
* Created By:Kunal Maheshwari
* Created on:
		***********************************************************************************************************************************************/
	boolean checkLoginName(String loginName) {
		 if(dao.getLoginNameCount(loginName)!=true)
			 throw new WrongValueException("Entered Login Name is already present, please enter another login Name");
		else 
			return true;
	}
}
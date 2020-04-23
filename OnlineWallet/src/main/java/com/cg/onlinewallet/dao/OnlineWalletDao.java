package com.cg.onlinewallet.dao;

import java.util.List;

import com.cg.onlinewallet.entities.WalletAccount;
import com.cg.onlinewallet.entities.WalletTransactions;
import com.cg.onlinewallet.entities.WalletUser;

public interface OnlineWalletDao {
	Integer persistUser(WalletUser user);
	WalletUser getUser(Integer userId);
	WalletAccount getAccount(Integer accountId);
	WalletTransactions getTransaction(Integer transactionId);
	boolean getLoginNameCount(String loginName);
	void persistAccount(WalletAccount account);
	void flush();
	
}
package com.cg.onlinewallet.dao;

import java.util.List;

import com.cg.onlinewallet.entities.WalletAccount;
import com.cg.onlinewallet.entities.WalletAccount.status;
import com.cg.onlinewallet.entities.WalletTransactions;
import com.cg.onlinewallet.entities.WalletUser;

public interface OnlineWalletDao {
	void saveUser(WalletUser user);
	WalletUser getUser(Integer userId);
	WalletAccount getAccount(Integer accountId);
	WalletTransactions getTransaction(Integer transactionId);
	void saveAccount(WalletAccount account);
	WalletUser getUserByLoginName(String loginName);
	boolean checkUserByLoginName(String loginName);
	void saveTransaction(WalletTransactions transaction);
	List<String> getActiveUserList();
	List<String> getNonActiveUserList();
	
}
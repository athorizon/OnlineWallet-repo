package com.cg.onlinewallet.dao;

import java.util.List;

import com.cg.onlinewallet.entities.WalletAccount;
import com.cg.onlinewallet.entities.WalletAccount.status;
import com.cg.onlinewallet.entities.WalletTransactions;
import com.cg.onlinewallet.entities.WalletUser;

public interface OnlineWalletDao {
	void persistUser(WalletUser user);
	WalletUser getUser(Integer userId);
	WalletAccount getAccount(Integer accountId);
	WalletTransactions getTransaction(Integer transactionId);
	void persistAccount(WalletAccount account);
	void flush();
	WalletUser getUserByLoginName(String loginName);
	boolean checkUserByLoginName(String loginName);
	void persistTransaction(WalletTransactions transaction);
	List<String> getActiveUserList();
	List<String> getNonActiveUserList();
	
}
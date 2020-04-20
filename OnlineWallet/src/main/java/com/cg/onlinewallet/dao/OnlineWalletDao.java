package com.cg.onlinewallet.dao;

import java.util.List;

import com.cg.onlinewallet.entities.WalletAccount;
import com.cg.onlinewallet.entities.WalletTransactions;
import com.cg.onlinewallet.entities.WalletUser;

public interface OnlineWalletDao {
	void persistUser(WalletUser user);
	WalletUser getUser(Integer userId);
	WalletAccount getAccount(Integer accountId);
	WalletTransactions getTransaction(Integer transactionId);
	int getLoginNameCount(String loginName);
	
}

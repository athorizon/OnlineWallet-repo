package com.cg.onlinewallet.service;

import com.cg.onlinewallet.entities.WalletUser;

public interface OnlineWalletService {
	Integer resgisterUser(WalletUser user);
    void addMoney(Integer userId, Double Amount);
	Double showBalance(Integer userId);
	void transactMoney(Integer userId, String beneficiaryLoginName, Double amount);
	Integer login(String loginName, String password);

}

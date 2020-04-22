package com.cg.onlinewallet.service;

import com.cg.onlinewallet.entities.WalletUser;

public interface OnlineWalletService {
	Integer resgisterUser(WalletUser user);
    void addMoney(Integer userId, Double Amount);
	Double showBalance(Integer userId);

}

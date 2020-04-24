package com.cg.onlinewallet.service;

import java.util.List;

import com.cg.onlinewallet.entities.WalletUser;

public interface OnlineWalletService {
	void resgisterUser(WalletUser user);
    void addMoney(Integer userId, Double Amount);
	Double showBalance(Integer userId);
	void transactMoney(Integer userId, String beneficiaryLoginName, Double amount);
	Integer login(String loginName, String password);
	Integer loginAdmin(String loginName, String password);
	List<String> getUserList(Integer userId,String userStatus);
	void changeUserStatus(Integer adminId, String loginName, String userStatus);
	void logout(Integer userId);

}

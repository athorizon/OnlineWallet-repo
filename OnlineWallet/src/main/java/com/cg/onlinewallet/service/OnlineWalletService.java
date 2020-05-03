package com.cg.onlinewallet.service;

import java.util.List;

import com.cg.onlinewallet.entities.WalletUser;

public interface OnlineWalletService {
	String registerUser(WalletUser user);

	Double addMoney(Integer userId, Double Amount);

	Double showBalance(Integer userId);

	void transactMoney(Integer userId, String beneficiaryLoginName, Double amount);

	Integer login(String loginName, String password);

	Integer loginAdmin(String loginName, String password);

	List<String> getUserList(Integer userId, String userStatus);

	String changeUserStatus(Integer adminId, String loginName, String userStatus);

}

package com.cg.onlinewallet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import org.springframework.stereotype.Repository;

import com.cg.onlinewallet.entities.*;
import com.cg.onlinewallet.entities.WalletAccount.status;
import com.cg.onlinewallet.exceptions.UnauthorizedAccessException;

@Repository
public class OnlineWalletDaoImp implements OnlineWalletDao {
	@PersistenceContext
	private EntityManager entityManager;

	public OnlineWalletDaoImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void saveUser(WalletUser user) {
		// TODO Auto-generated method stub
		entityManager.persist(user);
	}

	@Override
	public void saveAccount(WalletAccount account) {
		entityManager.persist(account);
	}

	@Override
	public void saveTransaction(WalletTransactions transaction) {
		entityManager.persist(transaction);
	}
    
	
	@Override
	public boolean checkUserByEmail(String email) { // return false if the user is not present;
		String Qstr = "SELECT user.email FROM WalletUser user WHERE user.email= :email";
		TypedQuery<String> query = entityManager.createQuery(Qstr, String.class).setParameter("email", email);
		try {
			query.getSingleResult();
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	@Override
	public WalletUser getUserByEmail(String email) {
		String Qstr = "SELECT user FROM WalletUser user WHERE user.email= :email";
		TypedQuery<WalletUser> query = entityManager.createQuery(Qstr, WalletUser.class).setParameter("email",
				email);
		return query.getSingleResult();
	}

	@Override
	public List<String> getActiveUserList() {
		String Qstr = "SELECT user.email FROM WalletUser user JOIN user.accountDetail account WHERE account.userStatus= :userStatus";
		TypedQuery<String> query = entityManager.createQuery(Qstr, String.class).setParameter("userStatus",
				status.active);
		List<String> userList;
		try {
			userList = query.getResultList();
		} catch (Exception exception) {
			throw new UnauthorizedAccessException("No user Exist for given criteria");
		}
		return userList;
	}

	@Override
	public List<String> getNonActiveUserList() {
		String Qstr = "SELECT user.email FROM WalletUser user JOIN user.accountDetail account WHERE account.userStatus= :userStatus";
		TypedQuery<String> query = entityManager.createQuery(Qstr, String.class).setParameter("userStatus",
				status.non_active);
		List<String> userList;
		try {
			userList = query.getResultList();
		} catch (Exception exception) {
			throw new UnauthorizedAccessException("No user Exist for given criteria");
		}
		return userList;
	}

	@Override
	public WalletUser getUser(Integer userId) {
		WalletUser user = entityManager.find(WalletUser.class, userId);
		return user;
	}

	@Override
	public WalletAccount getAccount(Integer accountId) {
		WalletAccount account = entityManager.find(WalletAccount.class, accountId);
		return account;
	}

	@Override
	public WalletTransactions getTransaction(Integer transactionId) {
		WalletTransactions transaction = entityManager.find(WalletTransactions.class, transactionId);
		return transaction;
	}
}
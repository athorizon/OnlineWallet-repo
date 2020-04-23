package com.cg.onlinewallet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;


import com.cg.onlinewallet.entities.*;

@Repository
public class OnlineWalletDaoImp implements OnlineWalletDao {
    @PersistenceContext
	private EntityManager entityManager;
	public OnlineWalletDaoImp() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public Integer persistUser(WalletUser user) {
		// TODO Auto-generated method stub
		entityManager.persist(user);
		return user.getUserID();
	}
	
	@Override
	public void persistAccount(WalletAccount account)
	{
		entityManager.persist(account);
	}
	@Override
	public void flush()
	{
		entityManager.flush();
	}
    @Override
   	public boolean getLoginNameCount(String loginName)
   	{   
   		String Qstr="SELECT user.loginName FROM WalletUser user WHERE user.loginName= :loginName";
   		TypedQuery<String> query=entityManager.createQuery(Qstr,String.class).setParameter("loginName",loginName);
   		try
   		{
   			query.getSingleResult();
   		}
   		catch(Exception ex)
   		{
   			return true;
   		}
   		return false;
   	}
	
	@Override
	public WalletUser getUser(Integer userId)
	{   
		WalletUser user=entityManager.find(WalletUser.class, userId);
        return user;
	}
	
	@Override
	public WalletAccount getAccount(Integer accountId)
	{
		WalletAccount account=entityManager.find(WalletAccount.class, accountId);
		return account;
	}
	@Override
	public WalletTransactions getTransaction(Integer transactionId)
	{
		WalletTransactions transaction=entityManager.find(WalletTransactions.class, transactionId);
		return transaction;
	}
}

package com.cg.onlinewallet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;


import com.cg.onlinewallet.entities.*;
@Transactional
@Repository
public class OnlineWalletDaoImp implements OnlineWalletDao {
    @PersistenceContext
	EntityManager entityManager;
	public OnlineWalletDaoImp() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void persistUser(WalletUser user) {
		// TODO Auto-generated method stub
		entityManager.persist(user);
	}
	
    @Override
   	public int getLoginNameCount(String loginName)
   	{   
   		String Qstr="SELECT COUNT(user) FROM WalletUser user WHERE user.loginName=loginName";
   		TypedQuery<WalletUser> query=entityManager.createQuery(Qstr,WalletUser.class);
   		return query.getFirstResult();
   	}
	/*@Override
	public List<WalletAccount> getAccountList()
	{
		String Qstr="SELECT account FROM WalletAccount account";
		TypedQuery<WalletAccount> query=entityManager.createQuery(Qstr,WalletAccount.class);
		return query.getResultList();
	}
	@Override
	public List<WalletTransactions> getTransactionList()
	{
		String Qstr="SELECT transaction FROM WalletTransactions transaction";
		TypedQuery<WalletTransactions> query=entityManager.createQuery(Qstr,WalletTransactions.class);
		return query.getResultList();
	}*/
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

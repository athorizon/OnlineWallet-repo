/************************************************************************************************************************************************************************************************************************
 * @author Arushi Bhardwaj
 * Description: It is a spring repository class which provides the mechanism for storage,retrieval,search and update operations the objects managed by the entitymanager.
 * Created Date:
 *************************************************************************************************************************************************************************************************************************/
package com.cg.onlinewallet.dao;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

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
	public void persistUser(WalletUser user) {
		// TODO Auto-generated method stub
		entityManager.persist(user);
	}
	
	@Override
	public void persistAccount(WalletAccount account)
	{
		entityManager.persist(account);
	}
	@Override
	public void persistTransaction(WalletTransactions transaction)
	{
		entityManager.persist(transaction);
	}
	@Override
	public void flush()
	{
		entityManager.flush();
	}
	
/*************************************************************************************************************************************************************************
* Method:checkUserByLoginName
* Description:It is used for getting the loginname from user and checking it in the database that if it is present or not i.e. the user is a registered user or not.
* returns Boolean: true if the loginname is present otherwise it will return false.
* Created By:Arushi Bhardwaj
* Created on:
*********************************************************************************************************************************************************************/
    @Override
   	public boolean checkUserByLoginName(String loginName)
   	{   //return false if the user is not present;
   		String Qstr="SELECT user.loginName FROM WalletUser user WHERE user.loginName= :loginName";
   		TypedQuery<String> query=entityManager.createQuery(Qstr,String.class).setParameter("loginName",loginName);
   		try
   		{
   			query.getSingleResult();
   		}
   		catch(Exception ex)
   		{
   			return false;
   		}
   		return true;
   	}

	@Override 
	public WalletUser getUserByLoginName(String loginName)
	{
		String Qstr="SELECT user FROM WalletUser user WHERE user.loginName= :loginName";
   		TypedQuery<WalletUser> query=entityManager.createQuery(Qstr,WalletUser.class).setParameter("loginName",loginName);
   		return query.getSingleResult();
	}
	
	@Override
	public List<String> getActiveUserList()
	{
		String Qstr="SELECT user.loginName FROM WalletUser user JOIN user.accountDetail account WHERE account.userStatus= :userStatus";
		TypedQuery<String> query=entityManager.createQuery(Qstr,String.class).setParameter("userStatus", status.active);
		List<String> userList;
		try {
		userList= query.getResultList();
		}
		catch(Exception exception)
		{
			throw new UnauthorizedAccessException("No user Exist for given criteria");
		}
		return userList;
	}
	
	@Override
	public List<String> getNonActiveUserList()
	{
		String Qstr="SELECT user.loginName FROM WalletUser user JOIN user.accountDetail account WHERE account.userStatus= :userStatus";
		TypedQuery<String> query=entityManager.createQuery(Qstr,String.class).setParameter("userStatus", status.non_active);
		List<String> userList;
		try {
		userList= query.getResultList();
		}
		catch(Exception exception)
		{
			throw new UnauthorizedAccessException("No user Exist for given criteria");
		}
		return userList;
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

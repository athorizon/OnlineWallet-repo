/*********************************************************************************************************************************************************************************************
* @author:Kunal Maheshwari
* Description: It is a dao class which is used to access the data stored in our relational database.
**********************************************************************************************************************************************************************************************/
package com.cg.onlinewallet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import org.springframework.stereotype.Repository;

import com.cg.onlinewallet.entities.*;
import com.cg.onlinewallet.entities.WalletAccount.status;
import com.cg.onlinewallet.exceptions.InvalidException;
import com.cg.onlinewallet.exceptions.UnauthorizedAccessException;
import com.cg.onlinewallet.exceptions.ValidationException;

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
 
/*********************************************************************************************************************
* Method: checkUserByEmail
* Description: To check that whether a user is present with given email or not
* @param email:User's email
* @returns Boolean: true if the user is present with entered email, otherwise false
* Created By - Arushi Bhardwaj
***********************************************************************************************************************/
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
    
/*********************************************************************************************************************
* Method: getUserByEmail
* Description: To access the user with the given email
* @param email:User's email
* @returns user: It will return the user present with entered email
* Created By - Arushi Bhardwaj
***********************************************************************************************************************/

	@Override
	public WalletUser getUserByEmail(String email) {
		String Qstr = "SELECT user FROM WalletUser user WHERE user.email= :email";
		TypedQuery<WalletUser> query = entityManager.createQuery(Qstr, WalletUser.class).setParameter("email",
				email);
		return query.getSingleResult();
	}
	
/*********************************************************************************************************************
* Method: getActiveUserList
* Description: To access the list of users whose account is active in nature
* @returns userList: It will return the users whose accounts are active
* Created By - Kunal Maheshwari
***********************************************************************************************************************/
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
    
/*********************************************************************************************************************
 * * Method: getNonActiveUserList
   * Description: To access the list of users whose account is inactive in nature
   * @returns userList: It will return the users whose accounts are inactive
   * Created By - Kunal Maheshwari
   **********************************************************************************************************************/

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
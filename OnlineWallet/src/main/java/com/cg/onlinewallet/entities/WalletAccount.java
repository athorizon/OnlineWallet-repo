/************************************************************************************************************************************************************************************************************************
 * @author Kunal Maheshwari
 * Description: It is an entity class for Wallet Account(using @Entity that provides all the variables we are going to use in our project for adding money to account and showing the balance available in the account.
 *              It also gives the information about the primary key(accountID) using @id annotation and the relation with other tables in our database.
 * Created Date:
 *************************************************************************************************************************************************************************************************************************/
 package com.cg.onlinewallet.entities;
 

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
@Entity
@Table(name="UserAccount")
public class WalletAccount implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="acc_seq")
	private Integer accountID;
	private Double accountBalance;
//	enum status{active,non_active}
	@OneToMany(cascade=CascadeType.ALL)
	private List<WalletTransactions> transactionList;

/**********************************************************************************************************************************************
 * Method:getters(for eg:getAccountID()) and setters(for eg:setAccountID(int accountID))
 * Description:getters for retrieving and setters for updating the values of the variables.
 * Created By:Kunal Maheshwari
 * Created on:
 ***********************************************************************************************************************************************/

	public Integer getAccountID() {
		return accountID;
	}
	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}
	public Double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public WalletAccount(Double accountBalance, List<WalletTransactions> transactionList) {
		super();
		
		this.accountBalance = accountBalance;
		this.transactionList = transactionList;
	}
	
	public WalletAccount() {
		// TODO Auto-generated constructor stub
	}

}

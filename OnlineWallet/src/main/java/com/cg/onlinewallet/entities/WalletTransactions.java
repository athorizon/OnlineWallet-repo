/**************************************************************************************************************************************************************
 * @author Kunal Maheshwari
 * Description: It is an entity class for Wallet Transaction(using @Entity) that provides all the variables we are going to use in our project for transferring fund to another account.
 *              It also gives the information about the primary key(TransactionID) using @id annotation and the relation with other tables in our database.
 * Created Date:
 ***************************************************************************************************************************************************************/
package com.cg.onlinewallet.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "Transactions")
public class WalletTransactions implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tran_seq")
	private Integer transactionID;
	@Column(name="description",length=150)
	private String description;
	@Column(name="data_of_transaction")
	private LocalDateTime dateOfTransaction;
	@Column(name="amount",precision=2)
	private Double amount;
	@Column(name="balance",precision=2)
	private Double accountBalance;
	
/**********************************************************************************************************************************************
* Method:getters(for eg:getTransactionID()) and setters(for eg:setTransactionID(int accountID))
* Description:getters for retrieving and setters for updating the values of the variables.
* Created By:Kunal Maheshwari
* Created on:
***********************************************************************************************************************************************/
		

	public Integer getTransactionID() {
		return transactionID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(LocalDateTime dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public WalletTransactions(String description, LocalDateTime dateOfTransaction, Double amount,
			Double accountBalance) {
		super();

		this.description = description;
		this.dateOfTransaction = dateOfTransaction;
		this.amount = amount;
		this.accountBalance = accountBalance;
	}

	public WalletTransactions() {
		// TODO Auto-generated constructor stub
	}

}
	
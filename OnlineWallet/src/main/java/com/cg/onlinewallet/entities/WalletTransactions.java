package com.cg.onlinewallet.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;
@Entity
@Table(name="Transactions")
public class WalletTransactions implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tran_seq")
	private Integer transactionID;
	private String description;
	private LocalDateTime dateOfTransaction;
	private Double amount;
	private Double accountBalance;
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

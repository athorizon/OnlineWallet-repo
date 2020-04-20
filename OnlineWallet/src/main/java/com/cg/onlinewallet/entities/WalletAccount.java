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
	public Integer getAccountID() {
		return accountID;
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

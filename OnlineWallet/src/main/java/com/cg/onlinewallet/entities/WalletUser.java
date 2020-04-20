package com.cg.onlinewallet.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="UserDetail")
public class WalletUser implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="user_seq")
	private Integer userID;
	private String userName;
	private String password;
	private String phoneNumber;
	private String loginName;
	
	@OneToOne(cascade=CascadeType.ALL)
	WalletAccount accountDetail;
	public Integer getUserID() {
		return userID;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public WalletUser(String userName, String password, String phoneNumber, String loginName,WalletAccount accountDetail) {
		super();
		this.userName = userName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.loginName = loginName;
		this.accountDetail=accountDetail;
	}
	
	public WalletAccount getAccountDetail() {
		return accountDetail;
	}
	public void setAccountDetail(WalletAccount accountDetail) {
		this.accountDetail = accountDetail;
	}
	public WalletUser() {
		// TODO Auto-generated constructor stub
	}

}

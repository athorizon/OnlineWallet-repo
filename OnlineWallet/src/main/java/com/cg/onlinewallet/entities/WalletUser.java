/**************************************************************************************************************************************************************
 * @author Arushi Bhardwaj
 * Description: It is an entity class for Wallet User(using @Entity) that provides all the variables we are going to use in our project for registering a new user.
 *              It also gives the information about the primary key(UserID) using @id annotation and the relation with other tables in our database.
 * Created Date:
 ***************************************************************************************************************************************************************/
package com.cg.onlinewallet.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "UserDetail")
public class WalletUser implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	private Integer userID;
	@Column(name="user_name",length=30)
	private String userName;
	@Column(name="password",length=16)
	private String password;
	@Column(name="phone_number",length=10)
	private String phoneNumber;
	@Column(name="login_name",length=50)
	private String loginName;

	public enum type {
		admin, user
	};

	@Enumerated(EnumType.STRING)
	@Column(name="user_type")
	private type userType = type.user;

	@OneToOne(cascade = CascadeType.ALL)
	WalletAccount accountDetail;
	
/**********************************************************************************************************************************************
* Method:getters(for eg:getUserID()) and setters(for eg:setUserName(String userName))
* Description:getters for retrieving and setters for updating the values of the variables.
* Created By:Arushi Bhardwaj
* Created on:
***********************************************************************************************************************************************/
		

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

	public WalletUser(String userName, String password, String phoneNumber, String loginName, type userType,
			WalletAccount accountDetail) {
		super();
		this.userName = userName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.loginName = loginName;
		this.userType = userType;

		this.accountDetail = accountDetail;
	}

	public type getUserType() {
		return userType;
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


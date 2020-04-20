package com.cg.onlinewallet.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.onlinewallet.dao.*;
import com.cg.onlinewallet.entities.*;
import com.cg.onlinewallet.exceptions.*;


public class OnlineWalletUserImp implements OnlineWalletService {

	public OnlineWalletUserImp() {
		// TODO Auto-generated constructor stub
	}
    @Autowired
    OnlineWalletDao dao;
    
	@Override
	public Integer resgisterUser(WalletUser user) {
		// TODO Auto-generated method stub
		checkUserName(user.getUserName());
		checkPhoneNumber(user.getPhoneNumber());
		checkPassword(user.getPassword());
		checkLoginName(user.getLoginName());
		
		dao.persistUser(user);
		return null;
	}
	boolean checkUserName(String userName){
		if(userName==null) 
			throw new NullException("Entered value cannot be NULL");
		boolean userNamePattern=Pattern.matches("[a-zA-Z]",userName);
		 if(userNamePattern==false)
			throw new InvalidException("Entered username should contain alphabets only");
		else return true;
	}
	boolean checkPhoneNumber(String phoneNumber) {
		if(phoneNumber==null)
			throw new NullException("Entered value cannot be NULL");
		if(phoneNumber.length()!=10)
			throw new ValidationException("The phone Number should be of 10 digits");
		boolean phoneNumberPattern=Pattern.matches("[0-9]{10}",phoneNumber);
		 if(phoneNumberPattern==false) 
			throw new InvalidException("The phone number should only contain digits");
		else return true;
	}
	boolean checkPassword(String password) {
		if(password.length()<6)
			throw new ValidationException("The Password entered must be greater or equal to 6 characters");
		else if(password==null)
			throw new NullException("Entered value cannot be NULL");
		boolean passwordPattern=Pattern.matches("[a-zA-z0-9$&+,:;=?@#|'<>.-^*()%!]{6,12}",password);
		if(passwordPattern==false)
			throw new InvalidException("Entered password should be alphanumeric and must contain special characters");
		else return true;
	}
	boolean checkLoginName(String loginName) {
		if(loginName==null) 
			throw new NullException("Entered value cannot be NULL");
		boolean userNamePattern=Pattern.matches("[a-z0-9A-Z]{5,9}",loginName);
		 if(userNamePattern==false)
			throw new InvalidException("Entered Login Name should contain alphabets only");
		 if(dao.getLoginNameCount(loginName)!=0)
			 throw new WrongValueException("Entered Login Name is already present, please enter another login Name");
		else return true;
	}

}

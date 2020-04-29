package com.cg.onlinewallet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.onlinewallet.exceptions.*;
import com.cg.onlinewallet.service.OnlineWalletServiceImp;
@SpringBootTest
public class OnlineWalletApplicationsServiceTests {

	public OnlineWalletApplicationsServiceTests() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	OnlineWalletServiceImp service;
	
	@Test
	public void checkLoginStatusTest()
	{
		assertEquals(false,service.checkLoginStatus(52));
	}
	@Test
	public void checkLoginStatusTest1()
	{   Integer userid=service.login("kunalmaheshwari26@gmail.com", "Kunal@123");
		assertEquals(true,service.checkLoginStatus(userid));
		service.logout(userid);
	}
	@Test
	public void checkLoginNameTest()
	{
		assertEquals(true,service.checkLoginName("xyz123@gmail.com"));
	}
	@Test
	public void checkLoginNameTest1()
	{
		assertThrows(WrongValueException.class,()->{service.checkLoginName("arushi.b113@gmail.com");});
	}
	@Test
	public void checkBeneficiaryTest()
	{
		assertEquals(true,service.checkBeneficiary("kunalmaheshwari26@gmail.com"));
	}
	@Test
	public void checkBeneficiaryTest1()
	{
		assertThrows(InvalidException.class,()->{service.checkBeneficiary("xyz123@gmail.com");});
	}
	@Test
	public void checkBalanceLimitTest()
	{
		assertEquals(true,service.checkBalanceLimit(52,1000.0));
		
	}
	@Test
	public void checkBalanceLimitTest1()
	{
		assertThrows(WrongValueException.class,()->{service.checkBalanceLimit(52,2000.0);});
	}

}

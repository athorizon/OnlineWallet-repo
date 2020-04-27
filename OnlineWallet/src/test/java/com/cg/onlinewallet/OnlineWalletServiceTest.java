package com.cg.onlinewallet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.cg.onlinewallet.exceptions.InvalidException;
import com.cg.onlinewallet.exceptions.WrongValueException;
import com.cg.onlinewallet.service.OnlineWalletServiceImpl;

public class OnlineWalletServiceTest {
	
	@Test
	public void checkLoginStatusTest()
	{
		assertEquals(false,new OnlineWalletServiceImpl().checkLoginStatus(4));
	}
	@Test
	public void checkLoginStatusTest1()
	{
		Integer userid=new OnlineWalletServiceImpl().login("arushi.b113@gmail.com", "Arushi@123");
		assertEquals(true,new OnlineWalletServiceImpl().checkLoginStatus(userid));
		new OnlineWalletServiceImpl().logout(userid);
	}
	@Test
	public void checkLoginNameTest()
	{
		assertEquals(true,new OnlineWalletServiceImpl().checkLoginName("xyz123@gmail.com"));
	}
	@Test
	public void checkLoginNameTest1()
	{
		assertThrows(WrongValueException.class,()->{new OnlineWalletServiceImpl().checkLoginName("arushi.b113@gmail.com");});
	}
	@Test
	public void checkBeneficiaryTest()
	{
		assertEquals(true,new OnlineWalletServiceImpl().checkBeneficiary("kunalmaheshwari26@gmail.com"));
	}
	@Test
	public void checkBeneficiaryTest1()
	{
		assertThrows(InvalidException.class,()->{new OnlineWalletServiceImpl().checkBeneficiary("xyz123@gmail.com");});
	}
	@Test
	public void checkBalanceLimitTest()
	{
		assertEquals(true,new OnlineWalletServiceImpl().checkBalanceLimit(3,1000.0));
		
	}
	@Test
	public void checkBalanceLimitTest1()
	{
		assertThrows(WrongValueException.class,()->{new OnlineWalletServiceImpl().checkBalanceLimit(4,2000.0);});
	}

}

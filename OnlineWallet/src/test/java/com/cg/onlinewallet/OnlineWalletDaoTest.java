package com.cg.onlinewallet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.cg.onlinewallet.dao.OnlineWalletDaoImp;

public class OnlineWalletDaoTest {
	@Test
	public void checkUserByLoginNameTest()
	{
		assertEquals(false,new OnlineWalletDaoImp().checkUserByLoginName("xyz123@gmail.com"));
	}
	@Test
	public void checkUserByLoginNameTest1()
	{
		assertEquals(true,new OnlineWalletDaoImp().checkUserByLoginName("arushi.b113@gmail.com"));
	}

}

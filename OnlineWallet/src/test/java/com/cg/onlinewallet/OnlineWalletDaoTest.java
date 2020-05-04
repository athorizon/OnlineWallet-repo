package com.cg.onlinewallet;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cg.onlinewallet.dao.OnlineWalletDaoImp;
import com.cg.onlinewallet.entities.WalletUser;

@SpringBootTest
class OnlineWalletApplicationDaoTests {
	@Autowired
	OnlineWalletDaoImp dao;

	@Test
	void checkUserByLoginNameTestTrue() {
		assertTrue(dao.checkUserByEmail("kunalmaheshwari26@gmail.com"));
	}
	@Test
	void checkUserByLoginNameTestFalse() {
		assertFalse(dao.checkUserByEmail("testString"));
	}
	@Test
	void getUserByLoginNameTest()
	{   
		assertEquals(WalletUser.class,dao.getUserByEmail("User1@gmail.com").getClass());
	}

}

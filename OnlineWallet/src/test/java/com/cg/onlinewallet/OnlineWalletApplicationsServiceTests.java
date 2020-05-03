package com.cg.onlinewallet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.transaction.annotation.Transactional;

import com.cg.onlinewallet.dao.OnlineWalletDaoImp;
import com.cg.onlinewallet.entities.WalletUser;
import com.cg.onlinewallet.exceptions.*;
import com.cg.onlinewallet.service.OnlineWalletServiceImp;

@DirtiesContext(classMode= ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@Transactional
public class OnlineWalletApplicationsServiceTests {

	public OnlineWalletApplicationsServiceTests() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	OnlineWalletServiceImp onlineWalletService;
	@Autowired
	OnlineWalletDaoImp onlineWalletDao;
	
	WalletUser user;
	WalletUser admin;
	@BeforeEach
	public void assignObject()
	{
		user=onlineWalletDao.getUserByLoginName("kunalmaheshwari26@gmail.com");
		admin=onlineWalletDao.getUserByLoginName("Admin");
	}
	@Test
	public void addMoneyTest() {
		Double balance=user.getAccountDetail().getAccountBalance();
		Double updatedBalance=onlineWalletService.addMoney(user.getUserID(), 1000.00);
		balance+=1000.00;
		assertTrue(updatedBalance.equals(balance));
	}
	
	@Test
	public void showBalanceTest() {
		Double balance=user.getAccountDetail().getAccountBalance();
		assertTrue(balance.equals(onlineWalletService.showBalance(user.getUserID())));
	}
	
	@Test
	public void transactMoneyTest1() {
		assertThrows(InvalidException.class,()->{onlineWalletService.transactMoney(user.getUserID(), "ytwuegf",1000.00 );});
	}
	@Test
	public void transactMoneyTest2() {
		assertThrows(InvalidException.class,()->{onlineWalletService.transactMoney(user.getUserID(), "User1@gmail.com",1000.00 );});
	}
	@Test
	public void transactMoneyTest3() {
		Double balance=user.getAccountDetail().getAccountBalance();
		assertThrows(WrongValueException.class,()->{onlineWalletService.transactMoney(user.getUserID(), "arushi.b113@gmail.com", balance+1.00);});
	}
	@Test
	public void transactMoneyTest4() {
		Double balance=user.getAccountDetail().getAccountBalance();
		Double amount=balance-500.00;
		onlineWalletService.transactMoney(user.getUserID(), "arushi.b113@gmail.com",amount );
		balance-=500.00;
		assertTrue(user.getAccountDetail().getAccountBalance().equals(balance));
	}
	@Test
	public void transactMoneyTest5() {
		Double balance=user.getAccountDetail().getAccountBalance();
		WalletUser beneficiary=onlineWalletDao.getUserByLoginName("arushi.b113@gmail.com");
		Double beneficiaryBalance=beneficiary.getAccountDetail().getAccountBalance();
		onlineWalletService.transactMoney(user.getUserID(), "arushi.b113@gmail.com", balance-500.00);
		assertTrue(beneficiary.getAccountDetail().getAccountBalance().equals(beneficiaryBalance+500.00));
	}
	
	@Test
	public void loginTest1() {
		assertThrows(UnauthorizedAccessException.class,()->{onlineWalletService.login("User1@gmail.com", "User1@123");});
	}
	@Test
	public void loginTest2() {
		assertThrows(InvalidException.class,()->{onlineWalletService.login("Admin", "Admin123");});
	}
	@Test
	public void loginTest3() {
		assertThrows(ValidationException.class,()->{onlineWalletService.login("kunalmaheshwari26@gmail.com", "vjhsgdg");});
	}
	@Test
	public void loginTest4() {
		Integer userId=user.getUserID();
		Integer returnedUserId=onlineWalletService.login("kunalmaheshwari26@gmail.com", "Kunal@123");
		assertTrue(userId.equals(returnedUserId));
	}
	
	@Test
	public void loginAdminTest1() {
		assertThrows(UnauthorizedAccessException.class,()->{onlineWalletService.loginAdmin("User1@gmail.com", "User1@123");});
	}
	@Test
	public void loginAdminTest2() {
		assertThrows(ValidationException.class,()->{onlineWalletService.loginAdmin("Admin", "User1@123");});
	}
	@Test
	public void loginAdminTest3() {
		Integer adminId=admin.getUserID();
		Integer returnedAdminId=onlineWalletService.loginAdmin("Admin", "Admin123");
		assertTrue(adminId.equals(returnedAdminId));
	}
	@Test
	public void getUserListTest() {}
	
	@Test
	public void changeUserStatus() {}
}

/*********************************************************************************************************************************************************************************************
* @author:Kunal Maheshwari,Arushi Bhardwaj
* Description: It is a spring boot class used to bootstrap and launch a Spring application from a Java main method.
* It will do the auto-scanning of the components defined below
**********************************************************************************************************************************************************************************************/
package com.cg.onlinewallet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cg.onlinewallet.entities.*;
import com.cg.onlinewallet.entities.WalletAccount.status;
import com.cg.onlinewallet.entities.WalletUser.type;

@Transactional
@SpringBootApplication
public class OnlineWalletApplication implements CommandLineRunner {
	@Autowired
	EntityManager em;

	public static void main(String[] args) {
		SpringApplication.run(OnlineWalletApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	
		
		/*WalletTransactions walletTransaction1 = new WalletTransactions("amount debited", LocalDateTime.now(), 500.0, 500.0);
		WalletTransactions walletTransaction2 = new WalletTransactions("amount credited", LocalDateTime.now(), 500.0, 1000.0);
		WalletTransactions walletTransaction3 = new WalletTransactions("amount credited", LocalDateTime.now(), 500.0, 1500.0);
		WalletTransactions walletTransaction4 = new WalletTransactions("amount debited", LocalDateTime.now(), 500.0, 1000.0);
		List<WalletTransactions> list1 = new ArrayList<WalletTransactions>();
		List<WalletTransactions> list2 = new ArrayList<WalletTransactions>();
		list1.add(walletTransaction1);
		list1.add(walletTransaction2);
		list2.add(walletTransaction3);
		list2.add(walletTransaction4);
		WalletAccount walletAccount1 = new WalletAccount(1000.00, list1, status.active);
		WalletAccount walletAccount2 = new WalletAccount(1000.00, list2, status.non_active);
		WalletAccount walletAccount3 = new WalletAccount(0.0, new ArrayList<WalletTransactions>(), status.active);
		WalletAccount walletAccount4 = new WalletAccount(0.0, new ArrayList<WalletTransactions>(), status.non_active);
		WalletAccount walletAccount5 = new WalletAccount(0.0, new ArrayList<WalletTransactions>(), status.non_active);
		WalletUser walletUser1 = new WalletUser("Arushi Bhardwaj", "Arushi@123", "8728925856", "arushi.b113@gmail.com",
				type.user, walletAccount1);
		WalletUser walletUser2 = new WalletUser("Kunal Maheshwari","Kunal@123","9897446350","kunalmaheshwari26@gmail.com",type.user,walletAccount2);
		WalletUser walletUser3 = new WalletUser("Admin", "Admin@123", "1234567890", "Admin@gmail.com", type.admin, walletAccount3);
		WalletUser walletUser4 = new WalletUser("User1", "User1@123", "7894561230", "User1@gmail.com", type.user, walletAccount4);
		WalletUser walletUser5 = new WalletUser("User2", "User2@123", "7894561230", "User2@gmail.com", type.user, walletAccount5);
		em.persist(walletUser1);
		em.persist(walletUser2);
		em.persist(walletUser3);
		em.persist(walletUser4);
		em.persist(walletUser5);
		em.persist(walletAccount1);
		em.persist(walletAccount2);
		em.persist(walletAccount3);
		em.persist(walletAccount4);
		em.persist(walletAccount5);
		em.persist(walletTransaction1);
		em.persist(walletTransaction2);
		em.persist(walletTransaction3);
		em.persist(walletTransaction4);*/
		
		
	}
    
}
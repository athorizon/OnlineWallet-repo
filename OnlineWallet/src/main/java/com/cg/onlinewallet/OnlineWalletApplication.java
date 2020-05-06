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
		/*************************************************************************************************************
		 Data insertion below and should be removed when completed
		WalletTransactions wat1 = new WalletTransactions("amount debited", LocalDateTime.now(), 500.0, 500.0);
		WalletTransactions wat2 = new WalletTransactions("amount credited", LocalDateTime.now(), 500.0, 1000.0);
		WalletTransactions wat3 = new WalletTransactions("amount credited", LocalDateTime.now(), 500.0, 1500.0);
		WalletTransactions wat4 = new WalletTransactions("amount debited", LocalDateTime.now(), 500.0, 1000.0);

		List<WalletTransactions> list1 = new ArrayList<WalletTransactions>();
		List<WalletTransactions> list2 = new ArrayList<WalletTransactions>();

		list1.add(wat1);
		list1.add(wat2);
		list2.add(wat3);
		list2.add(wat4);
		WalletAccount wa1 = new WalletAccount(1000.00, list1, status.active);
		WalletAccount wa2 = new WalletAccount(1000.00, list2, status.non_active);
		WalletAccount wa3 = new WalletAccount(0.0, new ArrayList<WalletTransactions>(), status.active);
		WalletAccount wa4 = new WalletAccount(0.0, new ArrayList<WalletTransactions>(), status.non_active);
		WalletAccount wa5 = new WalletAccount(0.0, new ArrayList<WalletTransactions>(), status.non_active);

		WalletUser wu1 = new WalletUser("Kunal Maheshwari", "Kunal@123", "9897446350", "kunalmaheshwari26@gmail.com",
				type.user, wa1);
		WalletUser wu2 = new WalletUser("Aarushi Bhardwaj", "Aarushi@123", "9876543210", "arushi.b113@gmail.com",
				type.user, wa2);
		WalletUser wu3 = new WalletUser("Admin", "Admin@123", "1234567890", "Admin@capgemini.com", type.admin, wa3);
		WalletUser wu4 = new WalletUser("User1", "User1@123", "7894561230", "User1@gmail.com", type.user, wa4);
		WalletUser wu5 = new WalletUser("User2", "User2@123", "7894561230", "User2gmail.com", type.user, wa5);

		em.persist(wu1);
		em.persist(wu2);
		em.persist(wu3);
		em.persist(wu4);
		em.persist(wu5);

		em.persist(wa1);
		em.persist(wa2);
		em.persist(wa3);
		em.persist(wa4);
		em.persist(wa5);

		em.persist(wat1);
		em.persist(wat2);
		em.persist(wat3);
		em.persist(wat4);
		
		/**************************************************************************************************************/
	}
    
}

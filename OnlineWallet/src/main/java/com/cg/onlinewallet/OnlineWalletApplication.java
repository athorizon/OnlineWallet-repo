/************************************************************************************************************************************************************************************************************************
 * @author Arushi Bhardwaj
 * Description: It is a spring boot application for making a stand-alone application using entitymanager which will manage all the operations performed on the entities.
 * Created Date:
 *************************************************************************************************************************************************************************************************************************/
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
@Transactional
@SpringBootApplication
public class OnlineWalletApplication implements CommandLineRunner{
    @Autowired
    EntityManager em;
	public static void main(String[] args) {
		SpringApplication.run(OnlineWalletApplication.class, args);
	}
	
/**********************************************************************************************************************************************
* Method:run method of commandlinerunner interface
* Description:It is used for persisting and storing the data into our database.
* Created By:Kunal Maheshwari,Arushi Bhardwaj
* Created on:
* ***********************************************************************************************************************************************/

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		 WalletTransactions wat1=new WalletTransactions("amount debited",LocalDateTime.now(),500.0,500.0);
		 WalletTransactions wat2=new WalletTransactions("amount credited",LocalDateTime.now(),500.0,1000.0);
		 WalletTransactions wat3=new WalletTransactions("amount credited",LocalDateTime.now(),500.0,1500.0);
		 WalletTransactions wat4=new WalletTransactions("amount debited",LocalDateTime.now(),500.0,1000.0);
		 
		 
		 
		 List<WalletTransactions> list1=new ArrayList<WalletTransactions>();
		 List<WalletTransactions> list2=new ArrayList<WalletTransactions>();
		 
		 list1.add(wat1);
		 list1.add(wat2);
		 list2.add(wat3);
		 list2.add(wat4);
	     WalletAccount wa1=new WalletAccount(1000.00,list1,status.active);
	     WalletAccount wa2=new WalletAccount(1000.00,list2,status.non_active);
	     WalletAccount wa3=new WalletAccount(0.0,new ArrayList<WalletTransactions>(),status.active);
	     
	     WalletUser wu1=new WalletUser("Arushi Bhardwaj","arushi@123","8728925856","Arushi123",type.user,wa1);
		 WalletUser wu2=new WalletUser("Kunal Maheshwari","kunal@123","9897446350","Kunal123",type.user,wa2);
		 WalletUser wu3=new WalletUser("Admin","Admin123","1234567890","Admin",type.admin,wa3);
		 
		 em.persist(wu1);
	     em.persist(wu2);
	     em.persist(wu3);
	     
	     em.persist(wa1);
	     em.persist(wa2);
	     em.persist(wa3);
	     
	     em.persist(wat1);
		 em.persist(wat2);
		 em.persist(wat3);
		 em.persist(wat4);
	}
    
}
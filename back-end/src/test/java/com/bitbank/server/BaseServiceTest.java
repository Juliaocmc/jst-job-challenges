package com.bitbank.server;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.bitbank.BitbankApplication;
import com.bitbank.dao.AccountDao;
import com.bitbank.dao.ClientDao;

import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BitbankApplication.class)
@WebAppConfiguration
@Transactional

public abstract class BaseServiceTest {

	@Autowired
    ClientDao clientDao;

	@Autowired
    ClientService clientService;

	@Autowired
	AccountService accountService;
	
	@Autowired
	AccountDao accountDao;

	@PersistenceContext
	protected EntityManager entityManager;

	

	@After
	@Rollback(true)
	public void clearDatabase() {
		// clearDataBase();
	}

	
		
}

package com.bitbank.modelTest;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.bitbank.BitbankApplication;
import com.bitbank.dao.ClientDao;
import com.bitbank.server.ClientService;

import org.junit.After;
import org.junit.Before;
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
	

	
	@PersistenceContext
	protected EntityManager entityManager;

	@Before
	public void prepareDatabase() throws Exception {
		
	}

	@After
	@Rollback(true)
	public void clearDatabase() {
		// clearDataBase();
	}
		
}

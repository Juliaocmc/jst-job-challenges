package com.bitbank.repository;

import com.bitbank.dao.AccountDao;
import com.bitbank.model.Client;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;



public class ClientRepositoryTest extends BaseServiceTest{

    @Autowired
    AccountDao accountDao;

    
    @Test
    public void test1(){
        try {
        accountDao.getAccountNumber();

        Assert.assertEquals(1, 1);
        
    } catch (Exception e) {
        Assert.fail(e.getMessage());
    
    }

    }
    
}

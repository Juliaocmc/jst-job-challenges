package com.bitbank.server;

import com.bitbank.model.Account;

import java.util.List;

import com.bitbank.dao.AccountDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    
    @Autowired
    AccountDao ad;
    
    public void save(Account account){
        ad.save(account);
    }

    public Account getById(String account){
        return ad.getById(account);
    }

    public List<Account> findAll(){
        return ad.findAll();
    }

    public void update(Account accountId){
        ad.save(accountId);
    }

    public void delete(String account){
        ad.deleteById(account);
    }
}

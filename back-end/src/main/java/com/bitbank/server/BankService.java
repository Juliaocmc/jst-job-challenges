package com.bitbank.server;

import com.bitbank.model.Account;
import com.bitbank.model.Bank;
import com.bitbank.model.Client;

import java.util.List;

import com.bitbank.dao.BankDao;
import com.bitbank.dao.ClientDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BankService {
    
    @Autowired
    BankDao bankDao;

    @Autowired
    ClientDao clientDao;

    @Autowired
    AccountService accountService;
    
    public void save(Bank bank){
        bankDao.save(bank);
    }

    public Bank getById(String bankId){
        return bankDao.getById(bankId);
    }

    public List<Bank> findAll(){
        return bankDao.findAll();
    }

    public void update(Bank bank){
        bankDao.save(bank);
    }

    public void delete(String bankId){
        bankDao.deleteById(bankId);
    }

    public Account linkClientToAccount(Bank bank, Client client){
        client.getBankList().add(bank);
        var account = accountService.createAccount();        
        bank.getAccountList().add(account);
        bankDao.save(bank);     
        clientDao.save(client);
        return account;   
        
    }

    public List<Bank> getListBankByClient(String clientId){
        return bankDao.getListBankByClient(clientId);
    }
}

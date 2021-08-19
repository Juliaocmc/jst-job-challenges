package com.bitbank.server;

import com.bitbank.model.Account;
import com.bitbank.model.Bank;
import com.bitbank.model.Client;

import java.util.List;

import com.bitbank.dao.BankDao;
import com.bitbank.dao.ClientDao;
import com.bitbank.exception.BusinessException;
import com.bitbank.exception.RepositoryException;

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
    
    public void save(Bank bank) throws RepositoryException{
        try {
            
            bank.validate();
            bankDao.save(bank);
        } catch (BusinessException e) {            
            throw new RepositoryException(e.getFieldErros());
        }
    }

    public Bank getById(String bankId) throws RepositoryException, BusinessException{
        try {
            
            return bankDao.getById(bankId);
        } catch (Exception e) {            
            throw new BusinessException("Não foi possível buscar o banco por Id");
        }
    }

    public List<Bank> findAll(){
        try {
            
            return bankDao.findAll();
        } catch (Exception e) {
        }
        return null;
    }

    public void update(Bank bank){
        try {
            
            bankDao.save(bank);
        } catch (Exception e) {
        }
    }

    public void delete(String bankId){
        try {
            
            bankDao.deleteById(bankId);
        } catch (Exception e) {
        }
    }

    public Account linkClientToAccount(Bank bank, Client client){
        try {
            
            client.getBankList().add(bank);
            var account = accountService.createAccount();        
            bank.getAccountList().add(account);
            account.setClientId(client.getId());
            bankDao.save(bank);     
            clientDao.save(client);
            return account;   
        } catch (Exception e) {
        }
        return null;
        
    }

    public List<Bank> getListBankByClient(String clientId){
        try {
            
            return bankDao.getListBankByClient(clientId);
        } catch (Exception e) {
        }
        return null;
    }
}

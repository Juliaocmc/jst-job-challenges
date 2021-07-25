package com.bitbank.server;

import com.bitbank.model.Bank;

import java.util.List;

import com.bitbank.dao.BankDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {
    
    @Autowired
    BankDao bd;
    
    public void save(Bank bank){
        bd.save(bank);
    }

    public Bank getById(String bankId){
        return bd.getById(bankId);
    }

    public List<Bank> findAll(){
        return bd.findAll();
    }

    public void update(Bank bank){
        bd.save(bank);
    }

    public void delete(String bankId){
        bd.deleteById(bankId);
    }
}

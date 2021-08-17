package com.bitbank.server;

import com.bitbank.model.Account;
import com.bitbank.model.Bank;
import com.bitbank.model.Client;

import java.util.List;

import com.bitbank.dao.BankDao;
import com.bitbank.dao.ClientDao;
import com.bitbank.dto.ClientDto;
import com.bitbank.mapper.AccountMapper;
import com.bitbank.mapper.BankMapper;
import com.bitbank.mapper.ClientMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    
    @Autowired
    ClientDao cd;

    @Autowired
    BankMapper bankMapper;

    @Autowired
    ClientMapper clientMapper;

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    BankDao bankDao;
    
    public void save(Client client){
        cd.save(client);
    }

    public Client getById(String clientId){
        return cd.getById(clientId);
    }

    public List<Client> findAll(){
        return cd.findAll();
    }

    public void update(Client client){
        cd.save(client);
    }

    public void delete(String clientId){
        cd.deleteById(clientId);
    }

    public ClientDto relationshipBankClient(String clientId){
        var client = cd.getById(clientId);
        var clientDto = clientMapper.toDto(client);
        var bankList = bankDao.getListBankByClient(clientId);
        for (Bank bank : bankList) {
            var bankDto = bankMapper.toDto(bank);
            for (Account account : bank.getAccountList()){
                if (account.getClientId().equals(clientId)){
                    var accountDto = accountMapper.toDto(account);
                    bankDto.getAccountList().add(accountDto);                
                    
                }
            }
            clientDto.getListBank().add(bankDto);
            
        }   
        return clientDto;
    }

    
}

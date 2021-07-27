package com.bitbank.server;

import com.bitbank.model.Account;
import com.bitbank.model.Client;
import com.bitbank.model.Coin;

import java.util.ArrayList;
import java.util.List;

import com.bitbank.dao.AccountDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    
    @Autowired
    AccountDao accountDao;

    @Autowired
    CoinService coinService;

    @Autowired
    ClientService clientService;
    
    public void save(Account account){
        accountDao.save(account);
    }

    public Account getById(String account){
        return accountDao.getById(account);
    }

    public List<Account> findAll(){
        return accountDao.findAll();
    }

    public void update(Account accountId){
        accountDao.save(accountId);
    }

    public void delete(String account){
        accountDao.deleteById(account);
    }

    public Account createAccount(){
        var account = new Account();
        account.setNumber(accountDao.getAccountNumber() + 1L);
        accountDao.save(account);
        return account;
    }

    public List<Account> getAccountByClient(String clientId){        
        return accountDao.getAccountByClient(clientId);
    }

    public boolean clientHasAccount(String clientId, String accountId){
        var listAccoun = accountDao.getAccountByClient(clientId);
        for (Account account : listAccoun) {
            if(account.getId().equals(accountId)){
                return true;
            }
        }
        return false;
    }

    public void depositCoins(Coin coin, String accountId){
        var coinRecovered = coinService.getCoinByNameAndAccount(coin.getCoinName(), accountId);        
        if(coinRecovered == null){
            var account = accountDao.getById(accountId);
            List<Coin> coinsList = new ArrayList<>();
            coinService.save(coin);
            coinsList.add(coin);
            account.setCoinList(coinsList);
            save(account);
        } else{
            coinRecovered.setAmountCoins(coinRecovered.getAmountCoins() + coin.getAmountCoins());
            coinService.save(coin);
        }
    }
}

package com.bitbank.server;

import com.bitbank.model.Account;
import com.bitbank.model.Client;
import com.bitbank.model.Coin;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.bitbank.dao.AccountDao;
import com.bitbank.dao.CoinDao;
import com.bitbank.dto.BankBalanceDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    
    @Autowired
    AccountDao accountDao;

    @Autowired
    CoinExchangeService coinExchangeService;

    @Autowired
    CoinService coinService;

    @Autowired
    CoinDao coinDao;

    @Autowired
    ClientService clientService;

    @Autowired(required=true)
    CoinApiService coinApiService;
    
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
            coinService.save(coin);
            account.getCoinList().add(coin);
            save(account);
        } else{
            coinRecovered.setAmountCoins(coinRecovered.getAmountCoins() + coin.getAmountCoins());
            coinService.save(coinRecovered);
        }
    }

    public List<BankBalanceDto> getBankBalanceByAccount(Client client, Account account) throws IOException{
        List<Coin> coins = coinDao.getBankBalanceByAccount(account.getId());
        List<BankBalanceDto>  bankBalanceList = new ArrayList<>();
        for (Coin coin : coins){
            var coinApi = coinApiService.findCoinApiByName(coin.getCoinName());
            var bankBalance = new BankBalanceDto();
            bankBalance.setClientName(client.getName());
            bankBalance.setAccountNumber(account.getNumber());
            bankBalance.setAmountCoins(coin.getAmountCoins());
            bankBalance.setCoinName(coin.getCoinName());
            var price = Double.parseDouble(coinApi.getPriceUsd());
            bankBalance.setUsd(price * coin.getAmountCoins());
            bankBalance.setBrl(bankBalance.getUsd()/ Double.parseDouble(coinExchangeService.getBrlValue().getBrl()));
            bankBalanceList.add(bankBalance);
        }
        return bankBalanceList;
    }
    
}

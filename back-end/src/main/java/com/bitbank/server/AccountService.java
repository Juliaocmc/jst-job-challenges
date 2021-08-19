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
import com.bitbank.exception.BusinessException;
import com.bitbank.exception.RepositoryException;

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
    
    public void save(Account account) throws RepositoryException{
        try {
            account.validate();;
            accountDao.save(account);
        } catch (BusinessException e) {            
            throw new RepositoryException(e.getFieldErros());
        }
    }

    public Account getById(String account) throws BusinessException{
        try {
            
            return accountDao.getById(account);
        } catch (Exception e) {            
            throw new BusinessException("Não foi possível buscar o conta por Id");
        }
    }

    public List<Account> findAll() throws BusinessException{
        try {
            
            return accountDao.findAll();
        } catch (Exception e) {            
            throw new BusinessException("Não foi possível buscar todas contas");
        }
    }

    public void update(Account account) throws RepositoryException{
        try {
            account.validate();
            accountDao.save(account);
        } catch (BusinessException e) {            
            throw new RepositoryException(e.getFieldErros());
        }
    }

    public void delete(String account) throws BusinessException{
        try {
            
            accountDao.deleteById(account);
        } catch (Exception e) {            
            throw new BusinessException("Não foi possível deleter a conta");
        }
    }

    public Account createAccount() throws RepositoryException{
        try {
            
            var account = new Account();
            account.setNumber(accountDao.getAccountNumber() + 1L);
            account.validate();
            accountDao.save(account);
            return account;
        } catch (BusinessException e) {            
            throw new RepositoryException(e.getFieldErros());
        }
    }

    public List<Account> getAccountByClient(String clientId) throws BusinessException{      
        try {
            
            return accountDao.getAccountByClient(clientId);
        } catch (Exception e) {            
            throw new BusinessException("Não foi possível buscar as contas por ID");
        }
    }

    public boolean clientHasAccount(String clientId, String accountId) throws BusinessException{
        try {
            
            var listAccoun = accountDao.getAccountByClient(clientId);
            for (Account account : listAccoun) {
                if(account.getId().equals(accountId)){
                    return true;
                }
            }
            return false;
        } catch (Exception e) {            
            throw new BusinessException("Erro ao verifiar se a conta pertence ao cliente");
        }
    }

    public void depositCoins(Coin coin, String accountId) throws RepositoryException{
        try {
        var coinRecovered = coinService.getCoinByNameAndAccount(coin.getCoinName(), accountId);        
        if(coinRecovered == null){
            var account = accountDao.getById(accountId);
            coin.validate();            
            coinService.save(coin);
            account.getCoinList().add(coin);
            account.validate();
            save(account);
        } else{
            coinRecovered.setAmountCoins(coinRecovered.getAmountCoins() + coin.getAmountCoins());
            coinService.save(coinRecovered);
        }
    } catch (BusinessException e) {            
        throw new RepositoryException(e.getFieldErros());
    }
}

    public List<BankBalanceDto> getBankBalanceByAccount(Client client, Account account) throws IOException, BusinessException{
        try {
            
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
        } catch (Exception e) {            
            throw new BusinessException("Não foi possível varificar o balanço bancario por conta");
        }
    }
        
}

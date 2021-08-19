package com.bitbank.server;

import com.bitbank.model.Client;
import com.bitbank.model.Coin;

import java.util.List;

import com.bitbank.dao.ClientDao;
import com.bitbank.dao.CoinDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoinService {
    
    @Autowired
    CoinDao coinDao;
    
    public void save(Coin coin){
        try {
            coin.validate();
            coinDao.save(coin);
        } catch (Exception e) {
        }
    }

    public Coin getById(String coinId){
        try {
            
            return coinDao.getById(coinId);
        } catch (Exception e) {
        }
        return null;
    }

    public List<Coin> findAll(){
        try {
            
            return coinDao.findAll();
        } catch (Exception e) {
        }
        return null;
    }

    public void update(Coin coin){
        try {
            
            coinDao.save(coin);
        } catch (Exception e) {
        }
    }

    public void delete(String coinId){
        try {
            
            coinDao.deleteById(coinId);
        } catch (Exception e) {
        }
    }

    public Coin getCoinByNameAndAccount(String coinId, String accountId){
        try {
            
            return coinDao.getCoinByNameAndAccount(coinId, accountId);
        } catch (Exception e) {
        }
        return null;
    }
}

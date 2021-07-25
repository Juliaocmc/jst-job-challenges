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
    
    public void save(Coin coinId){
        coinDao.save(coinId);
    }

    public Coin getById(String coinId){
        return coinDao.getById(coinId);
    }

    public List<Coin> findAll(){
        return coinDao.findAll();
    }

    public void update(Coin coin){
        coinDao.save(coin);
    }

    public void delete(String coinId){
        coinDao.deleteById(coinId);
    }
}

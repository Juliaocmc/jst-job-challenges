package com.bitbank.dao;


import com.bitbank.model.Coin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinDao extends JpaRepository<Coin, String> {

    @Query( value = "SELECT * FROM coin where coin.coin_name = :coinName "+
    "and id = (SELECT coin_id FROM account_coin where account_id = :accountId)", nativeQuery = true)
    Coin getCoinByNameAndAccount(String coinName, String accountId);

}

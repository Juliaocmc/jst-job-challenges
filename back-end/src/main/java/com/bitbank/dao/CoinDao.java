package com.bitbank.dao;


import com.bitbank.model.Coin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinDao extends JpaRepository<Coin, String> {

    

}

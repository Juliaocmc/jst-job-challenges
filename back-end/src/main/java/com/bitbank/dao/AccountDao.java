package com.bitbank.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.bitbank.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao extends JpaRepository<Account, String> {

    
    @Query( value = "SELECT count(*) FROM account", nativeQuery = true)
    Integer getAccountNumber();

    @Query( value = "SELECT * FROM account a where a.id in (select account_id from client_account_rel  car where car.client_id = :clientId)", nativeQuery = true)
    List<Account> getAccountByClient(String clientId);

    
}

package com.bitbank.dao;

import java.util.List;

import com.bitbank.model.Bank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDao extends JpaRepository<Bank, String> {

    @Query(value="SELECT * FROM bank b WHERE b.id in (select bank_id from bank_client_rel  bcr where bcr.client_id = :clientId);", nativeQuery = true)
    List<Bank> getListBankByClient(String clientId);

}

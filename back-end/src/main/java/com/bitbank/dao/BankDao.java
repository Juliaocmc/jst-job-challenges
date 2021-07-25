package com.bitbank.dao;

import com.bitbank.model.Bank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDao extends JpaRepository<Bank, String> {


}

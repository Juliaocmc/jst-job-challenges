package com.bitbank.dao;


import com.bitbank.model.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDao extends JpaRepository<Client, String> {

    Client findByLogin(String login);
    

}

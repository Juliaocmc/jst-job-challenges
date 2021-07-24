package com.bitbank.dao;

import java.util.List;
import java.util.Optional;

import com.bitbank.model.Client;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDao extends JpaRepository<Client, String> {

    Client findByLogin(String login);

}

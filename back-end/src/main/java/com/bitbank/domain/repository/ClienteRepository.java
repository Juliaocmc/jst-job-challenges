package com.bitbank.domain.repository;

import com.bitbank.domain.model.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Client, Long> {


}

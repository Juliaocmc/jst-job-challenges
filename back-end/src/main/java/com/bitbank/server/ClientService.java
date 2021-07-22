package com.bitbank.server;

import com.bitbank.model.Client;

import java.util.List;
import java.util.Optional;

import com.bitbank.dao.ClientDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    
    @Autowired
    ClientDao cd;
    
    public void save(Client client) throws Exception{
        cd.save(client);
    }

    public Optional<Client> findById(String clientId){
        return cd.findById(clientId);
    }

    public List<Client> findAll(){
        return cd.findAll();
    }

    public void update(Client client){
        cd.save(client);
    }

    public void delete(String clientId){
        cd.deleteById(clientId);
    }
}

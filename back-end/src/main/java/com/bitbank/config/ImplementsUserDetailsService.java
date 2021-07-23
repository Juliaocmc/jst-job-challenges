package com.bitbank.config;


import com.bitbank.dao.ClientDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class ImplementsUserDetailsService implements UserDetailsService {
    

    @Autowired
    private ClientDao cd;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        var client = cd.findByLogin(username);

        if(client == null){
            throw new UsernameNotFoundException("User not found!");
        }
        return client;
    }
}

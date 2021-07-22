package com.bitbank.repository;

import com.bitbank.model.Client;

import org.junit.Assert;
import org.junit.Test;



public class ClientRepositoryTest extends BaseServiceTest{

    
    @Test
    public void test1(){
        try {
            
        Client client = new Client();
        client.setAdmin(true);
        client.setCpf(11111111111L);
        client.setEmail("user");
        client.setName("user");
        client.setPassword("user");
        client.setUsername("user");
        cs.save(client);

        Assert.assertNotNull(client);
        
    } catch (Exception e) {
        Assert.fail(e.getMessage());
    
    }

    }
}

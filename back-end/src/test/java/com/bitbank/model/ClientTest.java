package com.bitbank.model;

import com.bitbank.model.Client;

import org.junit.Assert;
import org.junit.Test;



public class ClientTest extends BaseServiceTest{

    
    @Test
    public void criar_client(){
        try {
            var client = new Client();
            client.setAdmin(true);
            client.setCpf(121314211111L);
            client.setEmail("ju@ju");
            client.setLogin("124545");
            client.setName("Jaca");
            client.setPassword("123");
            clientDao.save(client);        

            Assert.assertNotNull(client);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        
        }

    }

    @Test
    public void criar_client2(){
        try {
            var client = new Client();
            client.setAdmin(true);
            client.setCpf(121314211111L);
            client.setEmail("ju@ju");
            client.setLogin(null);
            client.setName("Jaca");
            client.setPassword(null);
            client.validate();

            Assert.assertNotNull(client);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        
        }

    }

}
    


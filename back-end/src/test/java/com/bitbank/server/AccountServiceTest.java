package com.bitbank.server;


import java.util.List;

import com.bitbank.exception.BusinessException;
import com.bitbank.model.Account;
import com.bitbank.model.Client;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;




public class AccountServiceTest extends BaseServiceTest{
    
    private Account account;
    private Client client;
    
    @Before
    public void prepare() throws BusinessException{
        client = new Client();
        client.setAdmin(true);
        client.setCpf("27618272018");
        client.setEmail("teste@teste.com.br");
        client.setLogin("123456");
        client.setName("Cliente Teste");
        client.setPassword("123456");
        client.validate();
        clientDao.save(client);

        account = new Account();
        account.setClientId(client.getId());
        account.setNumber(1L);
        
        
    }
    
    
    @Test
    public void testSave() {
        try {
            accountService.save(account);

            Assert.assertNotNull("Deve criar a conta com Id", account.getId());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }
    

    @Test
    public void testClientHasAccount() {

    }

    @Test
    public void testCreateAccount() {
        try{
            accountService.save(account);
            var novaConta = accountService.createAccount();
            Assert.assertNotNull("A nova conta deve ter no número 2", novaConta.getNumber());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testDelete() {
        try{
            accountService.save(account);

            var account2 = new Account();
            account2.setClientId(client.getId());
            account2.setNumber(2L);
            accountService.save(account2);
            List<Account> resultadoPre = accountService.findAll();

            Assert.assertEquals("O resultado tem que conter uma lista com 2 contas",
             2, resultadoPre.size());

            accountService.delete(account2.getId());

            List<Account> resultadoPos = accountService.findAll();

            Assert.assertEquals("O resultado tem que conter uma lista com 1 contas",
            1, resultadoPos.size());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    

    @Test
    public void testDepositCoins() {

    }

    @Test
    public void testFindAll() {
        try {
            accountService.save(account);

            var account2 = new Account();
            account2.setClientId(client.getId());
            account2.setNumber(2L);
            accountService.save(account2);
            List<Account> resultado = accountService.findAll();

            Assert.assertEquals("O resultado tem que conter uma lista com 2 contas",
             2, resultado.size());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testGetAccountByClient() {
        try{
        var accountList = accountService.getAccountByClient(client.getId());
        Assert.assertEquals("Deve buscar as todas as contas do cliente",
        client.getId(), accountList.get(0).getId());
    } catch (Exception e) {
       Assert.fail(e.getMessage());
   }
}

    @Test
    public void testGetBankBalanceByAccount() {

    }

    @Test
    public void testGetById() {
        try {
            accountService.save(account);
            var resultado = accountService.getById(account.getId());

            Assert.assertEquals("A conta e o resultado do getById devem ter o mesmo ID",
             account.getId(), resultado.getId());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Test
    public void testUpdate() {

    }
}

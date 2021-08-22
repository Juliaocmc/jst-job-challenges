package com.bitbank.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bitbank.exception.BusinessException;
import com.bitbank.exception.FieldError;

import org.apache.logging.log4j.util.Strings;

import org.junit.Assert;
import org.junit.Test;



public class ClientTest extends BaseModelTest{

    private String msg = "";
    
    @Test
    public void deveCriarClient(){
        try {
            var client = new Client();
            client.setAdmin(true);
            client.setCpf("27618272018");
            client.setEmail("teste@teste.com.br");
            client.setLogin("123456");
            client.setName("Cliente Teste");
            client.setPassword("123456");
            client.validate();
            clientDao.save(client);        

            Assert.assertNotNull(client);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        
        }

    }

    @Test
    public void naoValidarCPFInvalido() throws NoSuchFieldException, SecurityException{
        try {
            
            var client = new Client();
            client.setAdmin(true);
            client.setCpf("123456");
            client.setEmail("teste@teste.com.br");
            client.setLogin("123456");
            client.setName("Cliente Teste");
            client.setPassword("123456");
            client.validate();
            clientDao.save(client);   

            Assert.fail();
        }catch (BusinessException e) {
                for (FieldError fieldError : e.getListFieldError()) {
                    Assert.assertEquals("número do registro de contribuinte individual brasileiro (CPF) inválido", fieldError.getMessage());
                } 
                
            }
    }

    @Test
    public void naoValidarEmailNull() throws NoSuchFieldException, SecurityException{
        try {
            
            msg = Client.class.getDeclaredField("email").getAnnotation(NotNull.class).message();
            var client = new Client();
            client.setAdmin(true);
            client.setCpf("27618272018");
            client.setEmail(null);
            client.setLogin("123456");
            client.setName("Cliente Teste");
            client.setPassword("123456");
            client.validate();
            clientDao.save(client);   

            Assert.fail();
        }catch (BusinessException e) {
                for (FieldError fieldError : e.getListFieldError()) {
                    Assert.assertEquals(msg, fieldError.getMessage());
                } 
                
            }
    }


    @Test
    public void naoDeveriaValidarEmailIncorreto() throws NoSuchFieldException, SecurityException{
        try {
            
            msg = Client.class.getDeclaredField("email").getAnnotation(Email.class).message();
            var client = new Client();
            client.setAdmin(true);
            client.setCpf("27618272018");
            client.setEmail("teste");
            client.setLogin("123456");
            client.setName("Cliente Teste");
            client.setPassword("123456");
            client.validate();
            clientDao.save(client);   

                Assert.fail();
        }catch (BusinessException e) {
                for (FieldError fieldError : e.getListFieldError()) {
                    Assert.assertEquals("Deveria retornar erro de CPF inválido",msg, fieldError.getMessage());
                } 
                
            }
    }

    @Test
    public void naoValidarLoginNull() throws NoSuchFieldException, SecurityException{
    try {
        
        msg = Client.class.getDeclaredField("login").getAnnotation(NotNull.class).message();
        var client = new Client();
        client.setAdmin(true);
        client.setCpf("27618272018");
        client.setEmail("teste@teste.com.br");
        client.setLogin(null);
        client.setName("Cliente Teste");
        client.setPassword("123456");
        client.validate();
        clientDao.save(client);   

        Assert.fail();
    }catch (BusinessException e) {
            for (FieldError fieldError : e.getListFieldError()) {
                Assert.assertEquals(msg, fieldError.getMessage());
            } 
            
        }
    }

    @Test
    public void naoValidarPasswordNull() throws NoSuchFieldException, SecurityException{
        try {
            
            msg = Client.class.getDeclaredField("password").getAnnotation(NotNull.class).message();
            var client = new Client();
            client.setAdmin(true);
            client.setCpf("27618272018");
            client.setEmail("teste@teste.com.br");
            client.setLogin("123456");
            client.setName("Cliente Teste");
            client.setPassword(null);
            client.validate();
            clientDao.save(client);   

            Assert.fail();
        }catch (BusinessException e) {
                for (FieldError fieldError : e.getListFieldError()) {
                    Assert.assertEquals(msg, fieldError.getMessage());
                } 
                
            }
        }

    @Test
    public void naoValidarNomeNull() throws NoSuchFieldException, SecurityException{
        try {
            
            msg = Client.class.getDeclaredField("name").getAnnotation(NotNull.class).message();
            var client = new Client();
            client.setAdmin(true);
            client.setCpf("27618272018");
            client.setEmail("teste@teste.com.br");
            client.setLogin("123456");
            client.setName(null);
            client.setPassword("123456");
            client.validate();
            clientDao.save(client);   

            Assert.fail();
        }catch (BusinessException e) {
                for (FieldError fieldError : e.getListFieldError()) {
                    Assert.assertEquals(msg, fieldError.getMessage());
                } 
                
            }
        }

    @Test
    public void naoValidarNomeComMenosDe10Caracteres() throws NoSuchFieldException, SecurityException{
    try {
        
        msg = Client.class.getDeclaredField("name").getAnnotation(Size.class).message();
        var client = new Client();
        client.setAdmin(true);
        client.setCpf("27618272018");
        client.setEmail("teste@teste.com.br");
        client.setLogin("123456");
        client.setName("Cliente");
        client.setPassword("123456");
        client.validate();
        clientDao.save(client);   

        Assert.fail();
    }catch (BusinessException e) {
            for (FieldError fieldError : e.getListFieldError()) {
                Assert.assertEquals(msg, fieldError.getMessage());
            } 
            
        }
    }

    @Test
    public void naoValidarNomeComMaisde200Caracteres() throws NoSuchFieldException, SecurityException{
    try {
        
        msg = Client.class.getDeclaredField("name").getAnnotation(Size.class).message();
        var client = new Client();
        client.setAdmin(true);
        client.setCpf("27618272018");
        client.setEmail("teste@teste.com.br");
        client.setLogin("123456");
        client.setName(Strings.repeat("n", 201));
        client.setPassword("123456");
        client.validate();
        clientDao.save(client);   

        Assert.fail();
    }catch (BusinessException e) {
            for (FieldError fieldError : e.getListFieldError()) {
                Assert.assertEquals(msg, fieldError.getMessage());
            } 
            
        }
    }

   
}
    


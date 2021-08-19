package com.bitbank.server;

import com.bitbank.model.Account;
import com.bitbank.model.Bank;
import com.bitbank.model.Client;

import java.util.List;

import com.bitbank.dao.BankDao;
import com.bitbank.dao.ClientDao;
import com.bitbank.dto.ClientDto;
import com.bitbank.exception.BusinessException;
import com.bitbank.exception.RepositoryException;
import com.bitbank.mapper.AccountMapper;
import com.bitbank.mapper.BankMapper;
import com.bitbank.mapper.ClientMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    
    @Autowired
    ClientDao cd;

    @Autowired
    BankMapper bankMapper;

    @Autowired
    ClientMapper clientMapper;

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    BankDao bankDao;
    
    
    /** 
     * @param client
     * @throws RepositoryException
     */
    public void save(Client client) throws RepositoryException {
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            client.setPassword(passwordEncoder.encode(client.getPassword()));  
                client.validate();
                cd.save(client);
            } catch (BusinessException e) {
                throw new RepositoryException(e.getFieldErros());
            }
    }

    
    /** 
     * @param clientId
     * @return Client
     * @throws BusinessException
     * @throws RepositoryException
     */
    public Client getById(String clientId) throws BusinessException{
        try {
            
            return cd.getById(clientId);
        } catch (Exception e) {            
            throw new BusinessException("Não foi possível buscar o client por Id");
        }
    }

    
    /** 
     * @return List<Client>
     * @throws BusinessException
     */
    public List<Client> findAll() throws BusinessException{
        try {
            return cd.findAll();
        } catch (Exception e) {            
            throw new BusinessException("Não foi possível buscar todos clientes");
        }
    }

    
    /** 
     * @param client
     * @throws RepositoryException
     */
    public void update(Client client) throws RepositoryException{
    try {
        client.validate();
        cd.save(client);
    } catch (BusinessException e) {            
        throw new RepositoryException(e.getFieldErros());
    }
}
    
    /** 
     * @param clientId
     * @throws BusinessException
     */
    public void delete(String clientId) throws BusinessException{
        try {
            cd.deleteById(clientId);
        } catch (Exception e) {            
            throw new BusinessException("Não foi possível deleter o cliente");
        }
    }

    
    /** 
     * @param clientId
     * @return ClientDto
     * @throws BusinessException
     */
    public ClientDto relationshipBankClient(String clientId) throws BusinessException{
        try {
            var client = cd.getById(clientId);
            var clientDto = clientMapper.toDto(client);
            clientDto.getListBank().clear();
            var bankList = bankDao.getListBankByClient(clientId);
            for (Bank bank : bankList) {
                var bankDto = bankMapper.toDto(bank);
                for (Account account : bank.getAccountList()){
                    if (account.getClientId().equals(clientId)){
                        var accountDto = accountMapper.toDto(account);
                        bankDto.getAccountList().add(accountDto);                
                        
                    }
                }
                clientDto.getListBank().add(bankDto);
                
            }   
            return clientDto;
        } catch (Exception e) {            
            throw new BusinessException("Não foi possível relacionar o cliente com o banco");
        }
    }

    
}

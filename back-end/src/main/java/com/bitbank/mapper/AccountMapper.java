package com.bitbank.mapper;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import com.bitbank.dto.AccountDto;
import com.bitbank.model.Account;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class AccountMapper {

    @Autowired
    public ModelMapper modelMapper;

    public AccountDto toDto(Account account) {
        return modelMapper.map(account, AccountDto.class);
    }

    public Account toModel(AccountDto accountDto) {
        return modelMapper.map(accountDto, Account.class);
    }

    public List<AccountDto> toDtoList(List<Account> accounts){
        List<AccountDto> listDto = new ArrayList<>();
        for (Account account : accounts) {
            AccountDto accountDto = toDto(account);
            if (listDto != null) {
                listDto.add(accountDto);
                }
                }
                return listDto;
        }

        public List<Account> toModelList(List<AccountDto> accountsDto){
            List<Account> accountList = new ArrayList<>();
            for (AccountDto accountDto : accountsDto) {
                var account = toModel(accountDto);
                if (accountList != null) {
                    accountList.add(account);
                    }
                    }
                    return accountList;
            }
}
    


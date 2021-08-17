package com.bitbank.mapper;

import java.util.ArrayList;
import java.util.List;

import com.bitbank.dto.BankDto;
import com.bitbank.model.Bank;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankMapper {

    @Autowired
    public ModelMapper modelMapper;

    public BankDto toDto(Bank bank) {
        return modelMapper.map(bank, BankDto.class);
    }

    public Bank toModel(BankDto bankDto) {
        return modelMapper.map(bankDto, Bank.class);
    }

    public List<BankDto> toDtoList(List<Bank> banks){
        List<BankDto> listDto = new ArrayList<>();
        for (Bank bank : banks) {
            BankDto bankDto = toDto(bank);
            if (listDto != null) {
                listDto.add(bankDto);
                }
                }
                return listDto;
        }

        public List<Bank> toModelList(List<BankDto> banksDto){
            List<Bank> bankList = new ArrayList<>();
            for (BankDto bankDto : banksDto) {
                var bank = toModel(bankDto);
                if (bankList != null) {
                    bankList.add(bank);
                    }
                    }
                    return bankList;
            }
}
    


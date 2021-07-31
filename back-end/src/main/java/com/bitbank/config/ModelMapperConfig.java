package com.bitbank.config;

import com.bitbank.dto.BankDto;
import com.bitbank.model.Bank;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    
    @Bean
    public ModelMapper modelMapper() {
        
    return new ModelMapper();


}
}

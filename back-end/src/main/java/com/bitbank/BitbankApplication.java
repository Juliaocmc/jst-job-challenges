package com.bitbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// @EnableJpaRepositories({"com.bitbank.dao"})
// @ComponentScan({"com.bitbank"})
@SpringBootApplication
public class BitbankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitbankApplication.class, args);
	}

}

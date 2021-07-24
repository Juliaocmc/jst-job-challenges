package com.bitbank.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "coin")
public class Coin implements Serializable{

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @Column(name = "coin_name")
    @NotNull(message = "Coin name cannot be null")
    @Size(min = 1, max = 50, message = "Coin name must contain 1 to 50 characters")
    private String coinName;

    @Column(name = "amount_coins")
    @NotNull(message = "Bank balance cannot be null")
    private Long amountCoins;

}

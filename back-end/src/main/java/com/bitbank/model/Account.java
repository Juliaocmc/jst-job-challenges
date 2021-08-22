package com.bitbank.model;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account extends BaseModel {
    
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @NotNull(message = "Number of account cannot be null")
    private Long number;


    @Column(name = "CLIENT_ID")
    @NotNull(message = "client of account cannot be null")
    private String clientId;

    @OneToMany
    @JoinTable(name = "account_coin", joinColumns = @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID"), 
               inverseJoinColumns = @JoinColumn(name = "COIN_ID", referencedColumnName = "ID"))   
    private List<Coin> coinList;
    
    
}

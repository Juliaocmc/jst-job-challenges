package com.bitbank.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
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
@Table(name = "bank")
public class Bank implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;


    @Column(name = "name")
    @NotNull(message = "Name cannot be null")
    @Size(min = 10, max = 50, message = "name must contain 10 to 50 characters")
    private String name;

    @Column(name = "agency")
    @NotNull(message = "Agency number cannot be null")
    private Long agency;

    @OneToMany
    @JoinTable(name = "bank_account", joinColumns = @JoinColumn(name = "BANK_ID", referencedColumnName = "ID"), 
               inverseJoinColumns = @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID"))   
    private List<Account> accountList;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Bank other = (Bank) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    

}

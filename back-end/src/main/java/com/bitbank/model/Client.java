package com.bitbank.model;

import java.util.Collection;
import java.util.List;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.FieldError;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "client")
public class Client extends BaseModel implements UserDetails {
    
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @Column(name = "name")
    @NotNull(message = "Name cannot be null")
    @Size(min = 10, max = 200, message = "name must contain 10 to 200 characters")
    private String name;

    @Column(name = "login")
    @NotNull(message = "Login cannot be null")
    @Size(min = 5, max = 20, message = "login must contain 5 to 20 characters")
    private String login;

    @Column(name = "cpf")
    @NotNull(message = "CPF cannot be null")
    @CPF
    private String cpf;

    @Column(name = "email")
    @NotNull(message = "E-mail cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "password")
    @NotNull(message = "Password cannot be null")
    private String password;

    @NotNull
    private boolean admin;
   

    @ManyToMany
    @JoinTable(name = "client_bank", joinColumns = @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID", unique = false), 
               inverseJoinColumns = @JoinColumn(name = "BANK_ID", referencedColumnName = "ID" , unique = false))
    private List<Bank> bankList;

     
    
    /** 
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Client other = (Client) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

   
    
    /** 
     * @return Collection<? extends GrantedAuthority>
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @return boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    
    /** 
     * @return boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    
    /** 
     * @return boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    
    /** 
     * @return boolean
     */
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }


    
    /** 
     * @return String
     */
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.login;
    }

      
    
    
}

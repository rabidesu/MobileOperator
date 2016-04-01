package com.tsystems.jschool.mobile.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexandra on 18.03.2016.
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {
    public CurrentUser(String username, String password, boolean enabled, boolean accountNonExpired,
                             boolean credentialsNonExpired, boolean accountNonLocked,
                             Collection<? extends GrantedAuthority> authorities, int id, Date birthday,
                             String passport, String address, List<Contract> contracts, String name, String surname) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.passport = passport;
        this.address = address;
        this.contracts = contracts;
    }



    private int id;

    private String name;

    private String surname;

    private String email;

    private Date birthday;

    private String passport;

    private String address;

    private List<Contract> contracts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }


}

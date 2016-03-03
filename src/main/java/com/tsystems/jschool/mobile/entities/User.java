package com.tsystems.jschool.mobile.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name = User.GET_BY_EMAIL_PASSWD, query = "SELECT x FROM User x WHERE x.email = ?1 AND x.password = ?2"),
@NamedQuery(name = User.GET_BY_PHONE_NUMBER, query = "SELECT x FROM User x LEFT JOIN x.contracts c WHERE c.number LIKE ?1"),
        @NamedQuery(name = User.GET_BY_EMAIL, query = "SELECT x FROM User x WHERE x.email LIKE ?1"),
        @NamedQuery(name = User.GET_BY_SURNAME, query = "SELECT x FROM User x WHERE x.surname LIKE ?1")
})


public class User implements Serializable {

    public static final String GET_BY_EMAIL_PASSWD= "userGetByEmailAndPassword";
    public static final String GET_BY_PHONE_NUMBER= "userGetByPhoneNumber";
    public static final String GET_BY_EMAIL= "userGetByEmail";
    public static final String GET_BY_SURNAME= "userGetBySurname";

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "birthday", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "passport", nullable = false)
    private String passport;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Contract> contracts;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn (name = "role_id")
    private Role role;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

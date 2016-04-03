package com.tsystems.jschool.mobile.webservices.entities;

import java.util.List;


/**
 * Created by Alexandra on 02.04.2016.
 */
public class WebContract {

    private String phoneNumber;

    private String name;

    private String surname;

    private String email;

    private String tariff;

    private List<String> contractOptions;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public WebContract(String phoneNumber, String name, String surname, String email,
                       String tariff, List<String> contractOptions) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.tariff = tariff;
        this.contractOptions = contractOptions;
    }

    public WebContract() {
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
        this.tariff = tariff;
    }

    public List<String> getContractOptions() {
        return contractOptions;
    }

    public void setContractOptions(List<String> contractOptions) {
        this.contractOptions = contractOptions;
    }
}


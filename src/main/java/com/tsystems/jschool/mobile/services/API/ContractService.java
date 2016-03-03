package com.tsystems.jschool.mobile.services.API;

import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import java.util.List;

/**
 * Created by Alexandra on 26.02.2016.
 */
public interface ContractService {

    List<Contract> getAllContracts() throws MobileServiceException;

    void addContract(String name, String surname, String date, String passport, String address, String email, String password,
                            String phone_number, String tariff_id, String[] options) throws MobileServiceException;

    List<Contract> findContractByNumber(String number) throws MobileServiceException;

    Contract getContractById(String id) throws MobileServiceException;

    void changeContract(String id, String tariff, String[] options, String block) throws MobileServiceException;

    void addContractForUser(String user_id, String phone_number, String tariff, String[] options) throws MobileServiceException;

}

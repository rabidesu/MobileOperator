package com.tsystems.jschool.mobile.services.API;

import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.entities.Tariff;

import java.util.List;

/**
 * Created by Alexandra on 26.02.2016.
 */
public interface ContractService {

    List<Contract> getAllContracts();

    void addContract(String name, String surname, String date, String passport, String address, String email, String password,
                            String phone_number, String tariff_id, String[] options);

    List<Contract> findContractByNumber(String number);

    Contract getContractById(String id);

    void changeContract(String id, String tariff, String[] options, String block);

    void addContractForUser(String user_id, String phone_number, String tariff, String[] options);

}

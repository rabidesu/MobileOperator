package com.tsystems.jschool.mobile.dao.API;

import com.tsystems.jschool.mobile.entities.Contract;

import java.util.List;

/**
 * Created by Alexandra on 25.02.2016.
 */
public interface ContractDAO extends GenericDAO<Contract> {

    List<Contract> findContractByNumber(String number);

}

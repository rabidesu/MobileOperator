package com.tsystems.jschool.mobile.dao.API;

import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;


import javax.persistence.EntityManager;
import java.util.List;

public interface ContractDAO extends GenericDAO<Contract> {

    List<Contract> findContractByNumber(String number);

    long getCountContracts();

    long getCountContractsByNumber(String number);

    List<Contract> findContractPageByNumber(int page, int pageSize, String number);

    List<Contract> findContractWithTariff(int tariffId);

    List<Contract> getAllContractsWithTariff(int tariffId);

    List<Contract> findContractWithOption(int optionId);

    List<Contract> findContractPage(int page, int pageSize);

}

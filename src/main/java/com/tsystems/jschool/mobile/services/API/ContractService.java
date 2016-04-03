package com.tsystems.jschool.mobile.services.API;

import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.webservices.entities.WebContract;


import java.util.List;

/**
 * Created by Alexandra on 26.02.2016.
 */

public interface ContractService {

    void addContract(Contract contract);

    List<Contract> getContractsPage(int pageNumber, int pageSize);

    List<Contract> getContractsPageByNumber(int pageNumber, int pageSize, String number);

    int getCountContracts();

    int getCountContractsByNumber(String number);

    boolean isExistContractWithTariff(String tariffId);

    boolean isExistContractWithOption(String optionId);

    List<Contract> findContractByNumber(String number);

    Contract getContractById(String id);

    void changeContract(String contractId, Contract contract);

    void addContractForUser(Contract contract);

    void blockContractByAdmin(String contractId);

    void unblockContractByAdmin(String contractId);

    void blockContractByClient(String contractId);

    void unblockContractByClient(String contractId);

    List<WebContract> getAllContractsWithTariff(String tariffId);

}

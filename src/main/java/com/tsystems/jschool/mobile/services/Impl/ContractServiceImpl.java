package com.tsystems.jschool.mobile.services.Impl;


import com.tsystems.jschool.mobile.dao.API.*;
//import com.tsystems.jschool.mobile.repositories.ContractRepo;
import com.tsystems.jschool.mobile.entities.*;
import com.tsystems.jschool.mobile.enumerates.RoleName;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.API.ContractService;
import com.tsystems.jschool.mobile.webservices.entities.WebContract;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service("contractService")
public class ContractServiceImpl implements ContractService {

    private final static Logger logger = Logger.getLogger(ContractServiceImpl.class);

    @Autowired
    private TariffDAO tariffDAO;
    @Autowired
    private OptionDAO optionDAO;
    @Autowired
    private ContractDAO contractDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;


    @Transactional
    public List<Contract> getContractsPage(int pageNumber, int pageSize) {
        return contractDAO.findContractPage(pageNumber - 1, pageSize);
    }

    @Transactional
    public List<Contract> getContractsPageByNumber(int pageNumber, int pageSize, String number) {
        return contractDAO.findContractPageByNumber(pageNumber - 1, pageSize, "%" + number + "%");
    }

    @Transactional
    public int getCountContracts(){
        return (int)contractDAO.getCountContracts();
    }

    @Transactional
    public int getCountContractsByNumber(String number){
        return (int)contractDAO.getCountContractsByNumber("%" + number + "%");
    }

    @Transactional
    public void addContract(Contract contract){
        User user = contract.getUser();
        String salt = BCrypt.gensalt();
        String hashPassword = BCrypt.hashpw(user.getPassword(), salt);
        user.setPassword(hashPassword);
        user.setRole(roleDAO.getRoleByName(RoleName.ROLE_CLIENT));
        userDAO.save(user);
        if (!contract.getTariff().isAvailable()) {
            throw new MobileServiceException("Выбран недопустимый тариф");
        }
        for (Option option : contract.getOptions()){
            if (!option.isAvailable() || !contract.getTariff().getOptions().contains(option)){
                throw new MobileServiceException("Выбрана недопустимая опция");
            }
        }
        contractDAO.save(contract);
    }

    @Transactional
    public boolean isExistContractWithTariff(String tariffId){
        return !contractDAO.findContractWithTariff(Integer.valueOf(tariffId)).isEmpty();
    }

    @Transactional
    public boolean isExistContractWithOption(String optionId){
        return !contractDAO.findContractWithOption(Integer.valueOf(optionId)).isEmpty();
    }


    @Transactional
    public void addContractForUser(Contract contract) {
        contractDAO.merge(contract);
    }


    @Transactional
    public List<Contract> findContractByNumber(String number) {
        return contractDAO.findContractByNumber("%" + number + "%");
    }

    @Transactional
    public Contract getContractById(String id) {
        return contractDAO.findById(Contract.class, Integer.valueOf(id));
    }

    @Transactional
    public void changeContract(String contractId, Contract contract) {
        Contract changedContract = contractDAO.findById(Contract.class, Integer.valueOf(contractId));
        if (changedContract.isBlockedByAdmin() || changedContract.isBlockedByClient()) {
            throw new MobileServiceException("Недопустимая операция. Нельзя изменить заблокированный контракт");
        }
        Tariff newTariff = contract.getTariff();
        if (!newTariff.isAvailable() && newTariff != changedContract.getTariff()) {
            throw new MobileServiceException("Выбран недопустимый тариф");
        }
        changedContract.setTariff(newTariff);

        List<Option> newOptions = contract.getOptions();
        for (Option option : newOptions){
            if ((!option.isAvailable() || !newTariff.getOptions().contains(option))
                    && !changedContract.getOptions().contains(option)){
                throw new MobileServiceException("Выбрана недопустимая опция");
            }
        }
        changedContract.setOptions(newOptions);
        contractDAO.merge(changedContract);
    }

    @Transactional
    public void blockContractByAdmin(String contractId) {
        Contract contract = contractDAO.findById(Contract.class, Integer.valueOf(contractId));
        if (contract.isBlockedByAdmin()) throw new MobileServiceException("Недопустимая операция. Контракт уже заблокирован");
        contract.setBlockedByAdmin(true);
    }

    @Transactional
    public void unblockContractByAdmin(String contractId) {
        Contract contract = contractDAO.findById(Contract.class, Integer.valueOf(contractId));
        if (!contract.isBlockedByAdmin()) throw new MobileServiceException("Недопустимая операция. Контракт уже активен");
        contract.setBlockedByAdmin(false);
    }

    @Transactional
    public void blockContractByClient(String contractId) {
        Contract contract = contractDAO.findById(Contract.class, Integer.valueOf(contractId));
        if (contract.isBlockedByAdmin()){
            throw new MobileServiceException("You can't change contract was blocked by administrator");
        }
        contract.setBlockedByClient(true);
    }

    @Transactional
    public void unblockContractByClient(String contractId) {
        Contract contract = contractDAO.findById(Contract.class, Integer.valueOf(contractId));
        if (contract.isBlockedByAdmin()){
            throw new MobileServiceException("You can't change contract was blocked by administrator");
        }
        contract.setBlockedByClient(false);
    }

    @Transactional
    public List<WebContract> getAllContractsWithTariff(String tariffId){
        return contractDAO.getAllContractsWithTariff(Integer.valueOf(tariffId)).stream()
                .map(e -> new WebContract(e.getNumber(), e.getUser().getName(), e.getUser().getSurname(),
                        e.getUser().getEmail(), e.getTariff().getName(),
                        e.getOptions().stream().map(Option::getName).collect(Collectors.toList()))).collect(Collectors.toList());
    }

}

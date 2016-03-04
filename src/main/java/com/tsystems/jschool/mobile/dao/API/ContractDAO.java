package com.tsystems.jschool.mobile.dao.API;

import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;

import javax.persistence.EntityManager;
import java.util.List;

public interface ContractDAO extends GenericDAO<Contract> {

    List<Contract> findContractByNumber(String number, EntityManager entityManager) throws MobileDAOException;

}

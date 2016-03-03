package com.tsystems.jschool.mobile.dao.API;

import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Alexandra on 25.02.2016.
 */
public interface ContractDAO extends GenericDAO<Contract> {

    List<Contract> findContractByNumber(String number, EntityManager entityManager) throws MobileDAOException;

}

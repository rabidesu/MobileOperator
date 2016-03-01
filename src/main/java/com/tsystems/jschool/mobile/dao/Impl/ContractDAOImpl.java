package com.tsystems.jschool.mobile.dao.Impl;

import com.tsystems.jschool.mobile.dao.API.ContractDAO;
import com.tsystems.jschool.mobile.entities.Contract;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Alexandra on 25.02.2016.
 */
public class ContractDAOImpl extends GenericDAOImpl<Contract> implements ContractDAO {

    public ContractDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    public List<Contract> findContractByNumber(String number){
        Query query = entityManager.createNamedQuery(Contract.GET_BY_NUMBER);
        query.setParameter(1, number);
        return findMany(query);
    }

}

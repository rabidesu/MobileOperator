package com.tsystems.jschool.mobile.dao.Impl;

import com.tsystems.jschool.mobile.dao.API.ContractDAO;
import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository("contractDAO")
public class ContractDAOImpl extends GenericDAOImpl<Contract> implements ContractDAO {

    @Autowired
    private EntityManager entityManager;

    private static Logger logger = Logger.getLogger(ContractDAOImpl.class);


    public List<Contract> findContractPage(int page, int pageSize){
        Query query = entityManager.createNamedQuery(Contract.GET_ALL);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public List<Contract> findContractPageByNumber(int page, int pageSize, String number){
        Query query = entityManager.createNamedQuery(Contract.GET_BY_NUMBER);
        query.setParameter(1, number);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public long getCountContracts() {
        Query query = entityManager.createNamedQuery(Contract.GET_COUNT);
        return (long) query.getSingleResult();
    }

    public long getCountContractsByNumber(String number) {
        Query query = entityManager.createNamedQuery(Contract.GET_COUNT_BY_NUMBER);
        query.setParameter(1, number);
        return (long) query.getSingleResult();
    }

    public List<Contract> findContractWithTariff(int tariffId) {
        Query query = entityManager.createNamedQuery(Contract.GET_WITH_TARIFF);
        query.setParameter(1, tariffId);
        query.setMaxResults(1);
        return findMany(query);
    }

    public List<Contract> getAllContractsWithTariff(int tariffId) {
        Query query = entityManager.createNamedQuery(Contract.GET_WITH_TARIFF);
        query.setParameter(1, tariffId);
        return findMany(query);
    }

    public List<Contract> findContractWithOption(int optionId) {
        Query query = entityManager.createNamedQuery(Contract.GET_WITH_OPTION);
        query.setParameter(1, optionId);
        query.setMaxResults(1);
        return findMany(query);
    }

    public List<Contract> findContractByNumber(String number) {
        Query query = entityManager.createNamedQuery(Contract.GET_BY_NUMBER);
        query.setParameter(1, number);
        return findMany(query);
    }
}

package com.tsystems.jschool.mobile.dao.Impl;

import com.tsystems.jschool.mobile.dao.API.ContractDAO;
import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Alexandra on 25.02.2016.
 */
public class ContractDAOImpl extends GenericDAOImpl<Contract> implements ContractDAO {

    private static Logger logger = Logger.getLogger(ContractDAOImpl.class);

    public List<Contract> findContractByNumber(String number, EntityManager entityManager) throws MobileDAOException {
        try {
            Query query = entityManager.createNamedQuery(Contract.GET_BY_NUMBER);
            query.setParameter(1, number);
            return findMany(query);
        } catch (Exception e){
            String message = "Error on find contract by number: " + number;
            logger.error(message);
            throw new MobileDAOException(message, e);
        }
    }

}

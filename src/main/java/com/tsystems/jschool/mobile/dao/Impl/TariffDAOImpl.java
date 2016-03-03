package com.tsystems.jschool.mobile.dao.Impl;

import com.tsystems.jschool.mobile.dao.API.TariffDAO;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Alexandra on 26.02.2016.
 */
public class TariffDAOImpl extends GenericDAOImpl<Tariff> implements TariffDAO {

    private static Logger logger = Logger.getLogger(TariffDAOImpl.class);

    public List<Tariff> findTariffByName(String name, EntityManager entityManager) throws MobileDAOException {
        try {
            Query query = entityManager.createNamedQuery(Tariff.GET_BY_NAME);
            query.setParameter(1, name);
            return findMany(query);
        } catch (Exception e){
            String message = "Error on get tariff by name: " + name;
            logger.error(message);
            throw new MobileDAOException(message, e);
        }
    }

    public void removeTariffById(int id, EntityManager entityManager) throws MobileDAOException {
        try {
            Query query = entityManager.createNamedQuery(Tariff.REMOVE_BY_ID);
            query.setParameter(1, id);
            query.executeUpdate();
        } catch (Exception e){
            String message = "Error on get tariff by id: " + id;
            logger.error(message);
            throw new MobileDAOException(message, e);
        }
    }
}

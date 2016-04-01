package com.tsystems.jschool.mobile.dao.Impl;

import com.tsystems.jschool.mobile.dao.API.TariffDAO;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

@Repository("tariffDAO")
public class TariffDAOImpl extends GenericDAOImpl<Tariff> implements TariffDAO {

    @Autowired
    private EntityManager entityManager;

    private static Logger logger = Logger.getLogger(TariffDAOImpl.class);

    public List<Tariff> findTariffByName(String name) {
        Query query = entityManager.createNamedQuery(Tariff.GET_BY_NAME);
        query.setParameter(1, name);
        return findMany(query);
    }

    public List<Tariff> getAvailableTariffs() {
        Query query = entityManager.createNamedQuery(Tariff.GET_ALL_AVAILABLE);
        return findMany(query);
    }

    public void removeTariffById(int id) {
        Query query = entityManager.createNamedQuery(Tariff.REMOVE_BY_ID);
        query.setParameter(1, id);
        query.executeUpdate();
    }
}

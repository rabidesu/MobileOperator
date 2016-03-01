package com.tsystems.jschool.mobile.dao.Impl;

import com.tsystems.jschool.mobile.dao.API.TariffDAO;
import com.tsystems.jschool.mobile.entities.Tariff;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Alexandra on 26.02.2016.
 */
public class TariffDAOImpl extends GenericDAOImpl<Tariff> implements TariffDAO {

    public TariffDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    public List<Tariff> findTariffByName(String name){
        Query query = entityManager.createNamedQuery(Tariff.GET_BY_NAME);
        query.setParameter(1, name);
        return findMany(query);
    }

    public void removeTariffById(int id){
        Query query = entityManager.createNamedQuery(Tariff.REMOVE_BY_ID);
        query.setParameter(1, id);
        query.executeUpdate();
    }
}

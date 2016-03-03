package com.tsystems.jschool.mobile.dao.API;

import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Alexandra on 26.02.2016.
 */
public interface TariffDAO extends GenericDAO<Tariff> {

    List<Tariff> findTariffByName(String name, EntityManager entityManager) throws MobileDAOException;

    void removeTariffById(int id, EntityManager entityManager) throws MobileDAOException;

}

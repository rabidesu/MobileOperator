package com.tsystems.jschool.mobile.dao.API;

import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;

import javax.persistence.EntityManager;
import java.util.List;

public interface TariffDAO extends GenericDAO<Tariff> {

    List<Tariff> findTariffByName(String name) throws MobileDAOException;

    void removeTariffById(int id);

    List<Tariff> getAvailableTariffs();

    List<Tariff> getTariffsWithOption(int optionId);


}

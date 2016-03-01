package com.tsystems.jschool.mobile.dao.API;

import com.tsystems.jschool.mobile.entities.Tariff;

import java.util.List;

/**
 * Created by Alexandra on 26.02.2016.
 */
public interface TariffDAO extends GenericDAO<Tariff> {

    List<Tariff> findTariffByName(String name);

    void removeTariffById(int id);

}

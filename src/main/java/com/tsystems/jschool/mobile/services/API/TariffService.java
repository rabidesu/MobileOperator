package com.tsystems.jschool.mobile.services.API;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.entities.Tariff;

import java.util.List;

/**
 * Created by Alexandra on 26.02.2016.
 */
public interface TariffService {

    void addTariff(String name, String price, String[] options);

    void changeTariff(String id, String name, String price, String[] options);

    List<Tariff> getAllTariffs();

    List<Tariff> findTariffByName(String name);

    Tariff getTariffById(String id);

    void removeTariffById(String id);

}

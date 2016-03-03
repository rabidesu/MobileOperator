package com.tsystems.jschool.mobile.services.API;

import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import java.util.List;

/**
 * Created by Alexandra on 26.02.2016.
 */
public interface TariffService {

    void addTariff(String name, String price, String[] options) throws MobileServiceException;

    void changeTariff(String id, String name, String price, String[] options) throws MobileServiceException;

    List<Tariff> getAllTariffs() throws MobileServiceException;

    List<Tariff> findTariffByName(String name) throws MobileServiceException;

    Tariff getTariffById(String id) throws MobileServiceException;

    void removeTariffById(String id) throws MobileServiceException;

}

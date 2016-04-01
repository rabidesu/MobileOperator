package com.tsystems.jschool.mobile.services.API;

import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import java.util.List;

/**
 * Created by Alexandra on 26.02.2016.
 */
public interface TariffService {

    List<Tariff> getAllTariffs() throws MobileServiceException;

    List<Tariff> findTariffByName(String name);

    Tariff getTariffById(String id);

    void removeTariffById(String id);

    void saveTariff(Tariff tariff);

    void changeTariff(String tariffId, Tariff tariff);

    List<Tariff> getAvailableTariffs();

    List<Tariff> getAvailableTariffsForContract(String contract_id);


}

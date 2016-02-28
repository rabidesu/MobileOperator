package dao.API;

import entities.Tariff;

import java.util.List;

/**
 * Created by Alexandra on 26.02.2016.
 */
public interface TariffDAO extends GenericDAO<Tariff, Integer> {

    public List<Tariff> findTariffByName(String name);

    public void removeTariffById(int id);

}

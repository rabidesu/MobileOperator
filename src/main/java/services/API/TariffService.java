package services.API;

import entities.Option;
import entities.Tariff;

import java.util.List;

/**
 * Created by Alexandra on 26.02.2016.
 */
public interface TariffService {

    public boolean addTariff(String name, String price, String[] options);

    public void changeTariff(String id, String name, String price, String[] options);

    public List<Tariff> getAllTariffs();

    public List<Tariff> findTariffByName(String name);

    public Tariff getTariffById(String id);

    public void removeTariffById(String id);

}

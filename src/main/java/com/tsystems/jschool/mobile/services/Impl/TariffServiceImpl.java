package com.tsystems.jschool.mobile.services.Impl;


import com.tsystems.jschool.mobile.dao.API.ContractDAO;
import com.tsystems.jschool.mobile.dao.API.OptionDAO;
import com.tsystems.jschool.mobile.dao.API.TariffDAO;
import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.webservices.entities.WebTariff;
import org.apache.log4j.Logger;
import com.tsystems.jschool.mobile.services.API.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("tariffService")
public class TariffServiceImpl implements TariffService {


    @Autowired
    private TariffDAO tariffDAO;
    @Autowired
    private OptionDAO optionDAO;
    @Autowired
    private ContractDAO contractDAO;

    @Transactional
    public void saveTariff(Tariff tariff){
        for (Option option : tariff.getOptions()){
            if (!option.isAvailable()){
                throw new MobileServiceException("Выбрана недоступная опция " + option.getName());
            }
        }
        tariffDAO.save(tariff);
    }

    @Transactional
    public void changeTariff(String tariffId, Tariff tariff){

        Tariff oldTariff = tariffDAO.findById(Tariff.class, Integer.valueOf(tariffId));
        if (!oldTariff.isAvailable()) {
            throw new MobileServiceException("Нельзя изменить недоступный тариф");
        }

        if (!contractDAO.findContractWithTariff(Integer.valueOf(tariffId)).isEmpty()){
            if (tariff.getPrice() != oldTariff.getPrice() && tariff.getPrice() != 0 ){
                throw new MobileServiceException("Нельзя изменить стоимость используемого тарифа");
            }
            if (tariff.getName() != null && !oldTariff.getName().equals(tariff.getName())){
                throw new MobileServiceException("Нельзя изменить название используемого тарифа");
            }
            for (Option opt : oldTariff.getOptions()){
                if (!tariff.getOptions().contains(opt)){
                    throw new MobileServiceException("Нельзя удалять опции из используемого тарифа");
                }
            }
            tariff.setName(oldTariff.getName());
            tariff.setPrice(oldTariff.getPrice());
        }
        for (Option option : tariff.getOptions()){
            if (!option.isAvailable()){
                throw new MobileServiceException("Выбрана недоступная опция " + option.getName());
            }
        }
        tariffDAO.merge(tariff);
    }

    @Transactional
    public List<Tariff> getAllTariffs() {
        return tariffDAO.findAll(Tariff.class);
    }

    @Transactional
    public List<Tariff> getAvailableTariffs(){
        return tariffDAO.getAvailableTariffs();
    }

    @Transactional
    public List<Tariff> getAvailableTariffsForContract(String contract_id){
        List<Tariff> tariffs = tariffDAO.getAvailableTariffs();
        Contract contract = contractDAO.findById(Contract.class, Integer.valueOf(contract_id));
        if (!tariffs.contains(contract.getTariff())){
            tariffs.add(contract.getTariff());
        }
        return tariffs;
    }

    @Transactional
    public List<Tariff> findTariffByName(String name) {
        return tariffDAO.findTariffByName("%" + name + "%");
    }

    @Transactional
    public Tariff getTariffById(String id) throws MobileServiceException {
        return tariffDAO.findById(Tariff.class, Integer.valueOf(id));
    }

    @Transactional
    public void removeTariffById(String id) throws MobileServiceException {
        if (contractDAO.findContractWithTariff(Integer.valueOf(id)).isEmpty()){
            tariffDAO.removeTariffById(Integer.valueOf(id));
        } else {
            Tariff tariff = tariffDAO.findById(Tariff.class, Integer.valueOf(id));
            tariff.setAvailable(false);
            tariffDAO.merge(tariff);
        }
    }

    public List<Tariff> getTariffWithOption(String optionId){
        return tariffDAO.getTariffsWithOption(Integer.valueOf(optionId));
    }

    public boolean existsTariffWithOption(String optionId){
        return (!tariffDAO.getTariffsWithOption(Integer.valueOf(optionId)).isEmpty());
    }

    @Transactional
    public List<WebTariff> getAllWebTariffs() {
        return tariffDAO.findAll(Tariff.class).stream()
                .map(e -> new WebTariff(e.getId(), e.getName())).collect(Collectors.toList());
    }

}

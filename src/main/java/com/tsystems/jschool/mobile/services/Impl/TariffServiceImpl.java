package com.tsystems.jschool.mobile.services.Impl;


import com.tsystems.jschool.mobile.dao.API.ContractDAO;
import com.tsystems.jschool.mobile.dao.API.OptionDAO;
import com.tsystems.jschool.mobile.dao.API.TariffDAO;
import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import org.apache.log4j.Logger;
import com.tsystems.jschool.mobile.services.API.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("tariffService")
public class TariffServiceImpl implements TariffService {

    private final static Logger logger = Logger.getLogger(TariffServiceImpl.class);

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
        if (!tariffDAO.findById(Tariff.class, Integer.valueOf(tariffId)).isAvailable()) {
            throw new MobileServiceException("Нельзя изменить недоступный тариф");
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

}

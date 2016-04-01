package com.tsystems.jschool.mobile.services.Impl;


import com.tsystems.jschool.mobile.dao.API.ContractDAO;
import com.tsystems.jschool.mobile.dao.API.OptionDAO;
import com.tsystems.jschool.mobile.dao.API.TariffDAO;
import com.tsystems.jschool.mobile.dao.JpaUtil;
import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.exceptions.CompatibilityOptionException;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.CompatibilityOptionChecker;
import org.apache.log4j.Logger;
import com.tsystems.jschool.mobile.services.API.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
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
    public void addTariff(String name, String price, String[] possibleOptions) throws MobileServiceException {

        Tariff tariff = new Tariff();

        if (name == null || name.isEmpty()) {
            String message = "Invalid tariff name: " + name;
            logger.error(message);
            throw new MobileServiceException(message);
        }
        tariff.setName(name);

        try {
            tariff.setPrice(Integer.valueOf(price));
        } catch (NumberFormatException e) {
            String message = "Invalid tariff price: " + price;
            logger.error(message);
            throw new MobileServiceException(message, e);
        }
        try {
            setOptionsForTariff(tariff, possibleOptions);
            tariffDAO.save(tariff);
        } catch (MobileDAOException | CompatibilityOptionException e) {
            throw new MobileServiceException(e.getMessage(), e);
        }
    }

    @Transactional
    public List<Tariff> getAllTariffs() throws MobileServiceException {
        try {
            List<Tariff> tariffs = tariffDAO.findAll(Tariff.class);
            return tariffs;
        } catch (MobileDAOException e) {
            throw new MobileServiceException(e);
        }
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
    public List<Tariff> findTariffByName(String name) throws MobileServiceException {
        try {
            List<Tariff> tariffs = tariffDAO.findTariffByName("%" + name + "%");
            return tariffs;
        } catch (MobileDAOException e) {
            throw new MobileServiceException(e);
        }
    }

    @Transactional
    public Tariff getTariffById(String id) throws MobileServiceException {
        try {
            Tariff tariff = tariffDAO.findById(Tariff.class, Integer.valueOf(id));
            return tariff;
        } catch (MobileDAOException e) {
            throw new MobileServiceException(e);
        }
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

    @Transactional
    public void changeTariff(String id, String name, String price, String[] possibleOptions) throws MobileServiceException {


        if (name.isEmpty() || price.isEmpty()) {
            String message = "Invalid parameters for tariff";
            logger.error(message);
            throw new MobileServiceException(message);
        }

        try {
            Tariff tariff = tariffDAO.findById(Tariff.class, Integer.valueOf(id));
            tariff.setName(name);
            tariff.setPrice(Integer.valueOf(price));
            setOptionsForTariff(tariff, possibleOptions);
        } catch (MobileDAOException | CompatibilityOptionException e) {
            throw new MobileServiceException(e.getMessage(), e);
        }
    }

    private void setOptionsForTariff(Tariff tariff, String[] possibleOptions)
            throws MobileDAOException, CompatibilityOptionException{

        List<Option> options = new ArrayList<>();
        if (possibleOptions != null) {
            for (String optionId : possibleOptions) {
                options.add(optionDAO.findById(Option.class, Integer.valueOf(optionId)));
            }
        }
        CompatibilityOptionChecker.checkAllRequiredOptionAvailable(options);
        tariff.setOptions(options);
    }
}

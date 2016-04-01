package com.tsystems.jschool.mobile.services.Impl;


import com.tsystems.jschool.mobile.dao.API.ContractDAO;
import com.tsystems.jschool.mobile.dao.API.OptionDAO;
import com.tsystems.jschool.mobile.dao.API.TariffDAO;
import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.API.OptionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("optionService")
public class OptionServiceImpl implements OptionService {

    private final static Logger logger = Logger.getLogger(OptionServiceImpl.class);

    @Autowired
    private OptionDAO optionDAO;
    @Autowired
    private ContractDAO contractDAO;
    @Autowired
    private TariffDAO tariffDAO;

    @Transactional
    public void removeOptionById(String id) {
        if (contractDAO.findContractWithOption(Integer.valueOf(id)).isEmpty()){
            optionDAO.removeOptionById(Integer.valueOf(id));
        } else {
            Option option = optionDAO.findById(Option.class, Integer.valueOf(id));
            option.setAvailable(false);
            for (Tariff tariff : tariffDAO.findAll(Tariff.class)){
                tariff.getOptions().remove(option);
            }

            //todo remove required
            optionDAO.merge(option);
        }
    }

    @Transactional
    public Option getOptionById(String id) {
        return optionDAO.findById(Option.class, Integer.valueOf(id));
    }

    @Transactional
    public void changeOption(String optionId, Option option){
        if (!optionDAO.findById(Option.class, Integer.valueOf(optionId)).isAvailable()) {
            throw new MobileServiceException("Нельзя изменить недоступную опцию");
        }
        for (Option incOption : option.getOptionsIncompatible()){
            if (!incOption.isAvailable()) {
                throw new MobileServiceException("Недоступная опция выбрана как несовместимая");
            }
        }
        for (Option reqOption : option.getOptionsRequired()){
            if (!reqOption.isAvailable()) {
                throw new MobileServiceException("Недоступная опция выбрана как требуемая");
            }
        }
        if (option.getOptionsRequired().contains(option) || option.getOptionsIncompatible().contains(option)){
            throw new MobileServiceException("Опция не может быть связана сама с собой");
        }
        Option oldOption = optionDAO.findById(Option.class, option.getId());
        List<Option> oldIncompatibleOptions = oldOption.getOptionsIncompatible();

        for (Option incOption : oldIncompatibleOptions){
            if (!option.getOptionsIncompatible().contains(incOption)){
                incOption.getOptionsIncompatible().remove(option);
                optionDAO.merge(incOption);
            }
        }
        for (Option incOption : option.getOptionsIncompatible()){
            incOption = optionDAO.findById(Option.class, incOption.getId());
            if (!incOption.getOptionsIncompatible().contains(option)){
                incOption.getOptionsIncompatible().add(option);
                optionDAO.merge(incOption);
            }
        }
        optionDAO.merge(option);
    }

    @Transactional
    public List<Option> getAllAnotherOptions(String id) {
        return optionDAO.getAnotherOptions(Integer.valueOf(id));
    }

    @Transactional
    public List<Option> getAvailableOptions() {
        return optionDAO.getAvailableOptions();
    }

    @Transactional
    public List<Option> getNotSelectedOption(String contractId) {
        Contract contract = contractDAO.findById(Contract.class, Integer.valueOf(contractId));
        List<Option> options = new ArrayList<>();
        options.addAll(contract.getTariff().getOptions());
        for (Option option : contract.getOptions()){
            if (options.contains(option)){
                options.remove(option);
            }
        }
        return options;
    }

    @Transactional
    public List<Option> getAllOptions() {
        return optionDAO.findAll(Option.class);
    }

    @Transactional
    public void saveOption(Option option){
        for (Option incOption : option.getOptionsIncompatible()){
            if (!incOption.isAvailable()) {
                throw new MobileServiceException("Недоступная опция выбрана как несовместимая");
            }
        }
        for (Option reqOption : option.getOptionsRequired()){
            if (!reqOption.isAvailable()) {
                throw new MobileServiceException("Недоступная опция выбрана как требуемая");
            }
        }
        optionDAO.save(option);
        for (Option optionIncompatible: option.getOptionsIncompatible()){
            optionIncompatible.getOptionsIncompatible().add(option);
            optionDAO.merge(optionIncompatible);
        }
    }


    @Transactional
    public List<Option> getOptionsByName(String name) throws MobileServiceException {
        return optionDAO.getOptionsByName("%" + name + "%");
    }


    public List<Option> getAllRequiredOption(Option option){
        List<Option> allRequiredOptions = new ArrayList<>();
        allRequiredOptions.addAll(getRequiredOption(option));
        return allRequiredOptions;
    }

    private List<Option> getRequiredOption (Option option){
        System.out.println("Option" + option);
        List<Option> allRequiredOptions = new ArrayList<>();
        for (Option requiredOption : option.getOptionsRequired()){
            allRequiredOptions.add(requiredOption);
            allRequiredOptions.addAll(getRequiredOption(requiredOption));
        }
        return allRequiredOptions;
    }

    public List<Option> getAllIncompatibleOption (Option option){
        List<Option> incompatibleOptions = new ArrayList<>();
        List<Option> requiredOptions = getAllRequiredOption(option);
        for (Option reqOption : requiredOptions){
            incompatibleOptions.addAll(reqOption.getOptionsIncompatible());
        }
        return incompatibleOptions;
    }

    public List<Option> getAllIncompatibleOption (Option option, List<Option> requiredOptions){
        List<Option> incompatibleOptions = new ArrayList<>();
        for (Option reqOption : requiredOptions){
            incompatibleOptions.addAll(reqOption.getOptionsIncompatible());
        }
        return incompatibleOptions;
    }


}

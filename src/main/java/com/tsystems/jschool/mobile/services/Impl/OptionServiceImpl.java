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

import javax.persistence.criteria.CriteriaBuilder;
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
            for (Option anotherOption : optionDAO.getAnotherOptions(Integer.valueOf(id))){
                if (anotherOption.getOptionsRequired().contains(option)){
                    anotherOption.getOptionsRequired().remove(option);
                }
            }
            optionDAO.merge(option);
        }
    }

    @Transactional
    public Option getOptionById(String id) {
        return optionDAO.findById(Option.class, Integer.valueOf(id));
    }

    @Transactional
    public void changeOption(String optionId, Option option){
        Option oldOption = optionDAO.findById(Option.class, option.getId());
        if (!oldOption.isAvailable()) {
            throw new MobileServiceException("Нельзя изменить недоступную опцию");
        }
        if (!contractDAO.findContractWithOption(Integer.valueOf(optionId)).isEmpty()){
            if (option.getPrice() != oldOption.getPrice() && option.getPrice() != 0 ){
                throw new MobileServiceException("Нельзя изменить стоимость используемой опции");
            }
            if (option.getName() != null && !oldOption.getName().equals(option.getName())){
                throw new MobileServiceException("Нельзя изменить название используемой опции");
            }
            for (Option opt : option.getOptionsIncompatible()){
                if (!oldOption.getOptionsIncompatible().contains(opt)){
                    throw new MobileServiceException("Нельзя добавлять несовместимые " +
                            "опции к используемой опции");
                }
            }
            for (Option opt : option.getOptionsRequired()){
                if (!oldOption.getOptionsRequired().contains(opt)){
                    throw new MobileServiceException("Нельзя добавлять зависимые " +
                            "опции к используемой опции");
                }
            }
            option.setPrice(oldOption.getPrice());
            option.setName(oldOption.getName());
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

        for (Option reqOption : option.getOptionsRequired()) {
            for (Tariff tariff : tariffDAO.getTariffsWithOption(Integer.valueOf(optionId))){
                if (!tariff.getOptions().contains(reqOption)){
                    tariff.getOptions().add(reqOption);
                }
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

    public boolean isRequiredAndIncompatibleIntersection(Option option){
        for (Option reqOption : option.getOptionsRequired()){
            if (option.getOptionsIncompatible().contains(reqOption)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOptionDependOnIncompatibleParent(Option option){
        for (Option reqOption : option.getOptionsRequired()){
            if (getAllIncompatibleOption(reqOption).contains(reqOption)) return true;
        }

        return false;
    }

    public boolean isInterdependentOptions(Option option){
        for (Option reqOption : option.getOptionsRequired()){
            List<Option> allRequiredOptions = getAllRequiredOption(reqOption);
            if (allRequiredOptions.contains(option)) return true;
        }

        return false;
    }

    public boolean isOptionIncompatibleWithParentRequired(Option option){
        List<Option> allRequiredOptions = getAllRequiredOption(option);
        for (Option incOption : option.getOptionsIncompatible()){
            if (allRequiredOptions.contains(incOption)) return true;
        }
        return false;
    }

    public boolean NotAllRequiredOptionAvailable(List<Option> options) {
        for (Option option : options) {
            for (Option reqOption : getAllRequiredOption(option)) {
                if (!options.contains(reqOption)) return true;
            }
        }
        return false;
    }

    public boolean isIncompatibleOptions(List<Option> options){
        for (Option option : options){
            for (Option incOption : option.getOptionsIncompatible()){
                if (options.contains(incOption)) return true;
            }
        }
        return false;
    }

    public List<Option> getAllRequiredOption(Option option){
        List<Option> allRequiredOptions = new ArrayList<>();
        allRequiredOptions.addAll(getRequiredOption(option));
        return allRequiredOptions;
    }

    private List<Option> getRequiredOption (Option option){
        List<Option> allRequiredOptions = new ArrayList<>();
        for (Option requiredOption : option.getOptionsRequired()){
            allRequiredOptions.add(requiredOption);
            allRequiredOptions.addAll(getRequiredOption(requiredOption));
        }
        return allRequiredOptions;
    }

    public List<Option> getAllIncompatibleOption (Option option){
        List<Option> incompatibleOptions = new ArrayList<>();
        incompatibleOptions.addAll(option.getOptionsIncompatible());
        List<Option> requiredOptions = getAllRequiredOption(option);
        for (Option reqOption : requiredOptions){
            incompatibleOptions.addAll(reqOption.getOptionsIncompatible());
        }
        return incompatibleOptions;
    }


}

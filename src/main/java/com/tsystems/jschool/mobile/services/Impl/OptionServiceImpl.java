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
import com.tsystems.jschool.mobile.services.API.OptionService;
import com.tsystems.jschool.mobile.services.CompatibilityOptionChecker;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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
    public void removeOptionById(String id) throws MobileServiceException {
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
    public Option getOptionById(String id) throws MobileServiceException {
        try {
            Option option = optionDAO.findById(Option.class, Integer.valueOf(id));
            return option;
        } catch (MobileDAOException e) {
            throw new MobileServiceException(e);
        }
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
    public List<Option> getAllAnotherOptions(String id) throws MobileServiceException {
        try {
            List<Option> options = optionDAO.getAnotherOptions(Integer.valueOf(id));
            return options;
        } catch (MobileDAOException e) {
            throw new MobileServiceException(e);
        }
    }

    @Transactional
    public List<Option> getAvailableOptions() throws MobileServiceException {
        try {
            return optionDAO.getAvailableOptions();
        } catch (MobileDAOException e) {
            throw new MobileServiceException(e);
        }
    }

    @Transactional
    public List<Option> getNotSelectedOption(String contractId) throws MobileServiceException {
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
    public List<Option> getAllOptions() throws MobileServiceException {
        try {
            List<Option> options = optionDAO.findAll(Option.class);
            return options;
        } catch (MobileDAOException e) {
            throw new MobileServiceException(e);
        }
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
    public void addOption(Option option, List<Integer> requiredOption, List<Integer> incompatibleOption)  {
        optionDAO.save(option);
        //setRequiredAndIncompatibleOptions(option, requiredOption, incompatibleOption);
       // optionDAO.merge(option);
    }

    @Transactional
    public List<Option> getOptionsByName(String name) throws MobileServiceException {
        try {
            List<Option> options = optionDAO.getOptionsByName("%" + name + "%");
            return options;
        } catch (MobileDAOException e) {
            throw new MobileServiceException(e);
        }
    }

    @Transactional
    public void changeOption(String id, String name, String price, String connectPrice,
                             List<Integer> requiredOption, List<Integer> incompatibleOption) throws MobileServiceException {


        if (name.isEmpty()) {
            String message = "Invalid name for option";
            logger.error(message);
            throw new MobileServiceException(message);
        }

        try {

            Option option = optionDAO.findById(Option.class, Integer.valueOf(id));
            option.setName(name);

            try {
                option.setPrice(Integer.valueOf(price));
                option.setConnectPrice(Integer.valueOf(connectPrice));
            } catch (NumberFormatException e) {
                String message = "Invalid parameters for option";
                logger.error(message);
                throw new MobileServiceException(message, e);
            }

            try {
                setRequiredAndIncompatibleOptions(option, requiredOption, incompatibleOption);
            } catch (CompatibilityOptionException e){
                logger.error(e.getMessage());
                throw new MobileServiceException(e.getMessage(), e);
            }

        } catch (MobileDAOException e) {
            throw new MobileServiceException(e);
        }
    }


    private void setRequiredAndIncompatibleOptions(Option option, List<Integer> requiredOption,
                                                   List<Integer> incompatibleOption) {

        List<Option> requiredOptions = new ArrayList<>();
        option.setOptionsRequired(requiredOptions);
        System.out.println("BEFORE FIND BY ID");
        if (requiredOption != null) {
            for (int requiredOptionId : requiredOption) {
                requiredOptions.add(optionDAO.findById(Option.class, requiredOptionId));
            }
        }

        System.out.println("BEFORE FIND ALL");
        List<Option> allOptions = optionDAO.findAll(Option.class);


        if (!allOptions.isEmpty() && !requiredOptions.isEmpty()) {
            CompatibilityOptionChecker.checkInterdependentOptions(option, allOptions, requiredOptions);
        }

        List<Option> newIncompatibleOptions = new ArrayList<>();
        option.setOptionsIncompatible(newIncompatibleOptions);
        System.out.println("BEFORE FIND BY ID 2");
        if (incompatibleOption != null) {
            for (int incompatibleOptionId : incompatibleOption) {
                Option incOption = optionDAO.findById(Option.class, incompatibleOptionId);
                newIncompatibleOptions.add(incOption);
                if (!incOption.getOptionsIncompatible().contains(option)) {
                    incOption.getOptionsIncompatible().add(option);
                    CompatibilityOptionChecker.checkRequiredAndIncompatibleIntersection(incOption);
                }
            }
        }

        System.out.println("BEFORE FIND BY ID 3");
        List<Option> oldIncompatibleOptions = option.getOptionsIncompatible();
        if (!oldIncompatibleOptions.isEmpty()) {
            for (Option oldInc : oldIncompatibleOptions) {
                if (!newIncompatibleOptions.contains(oldInc)) {
                    oldInc = optionDAO.findById(Option.class, oldInc.getId());
                    oldInc.getOptionsIncompatible().remove(option);
                }
            }
        }

        CompatibilityOptionChecker.checkIncompatibleWithRequiredOptions(allOptions, requiredOptions, newIncompatibleOptions);

        option.setOptionsRequired(requiredOptions);
        option.setOptionsIncompatible(newIncompatibleOptions);

        CompatibilityOptionChecker.checkRequiredAndIncompatibleIntersection(option);
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

//    public List<Option> getAllRequiredOption(Option option){
//        List<Option> allRequiredOptions = new ArrayList<>();
//        List<Option> allOptions = optionDAO.findAll(Option.class);
//        allRequiredOptions.addAll(getRequiredOption(option, allOptions));
//        return allRequiredOptions;
//    }
//
//    private List<Option> getRequiredOption (Option option, List<Option> allOptions){
//        List<Option> allRequiredOptions = new ArrayList<>();
//        for (Option requiredOption : option.getOptionsRequired()){
////            requiredOption = allOptions.get(allOptions.indexOf(requiredOption));
//            allRequiredOptions.add(requiredOption);
//            allRequiredOptions.addAll(getRequiredOption(requiredOption, allOptions));
//        }
//        return allRequiredOptions;
//    }


}

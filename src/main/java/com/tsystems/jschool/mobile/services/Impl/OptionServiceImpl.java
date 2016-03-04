package com.tsystems.jschool.mobile.services.Impl;

import com.tsystems.jschool.mobile.MobileContext;
import com.tsystems.jschool.mobile.dao.API.OptionDAO;
import com.tsystems.jschool.mobile.dao.JpaUtil;
import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.exceptions.CompatibilityOptionException;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.API.OptionService;
import com.tsystems.jschool.mobile.services.CompatibilityOptionChecker;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


public class OptionServiceImpl implements OptionService {

    private final static Logger logger = Logger.getLogger(OptionServiceImpl.class);

    private OptionDAO optionDAO;

    public OptionServiceImpl(MobileContext context) {
        optionDAO = context.optionDAO;
    }

    public void removeOptionById(String id) throws MobileServiceException {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            optionDAO.removeOptionById(Integer.valueOf(id), em);
            JpaUtil.commitTransaction(em);
        } catch (MobileDAOException e) {
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }

    public Option getOptionById(String id) throws MobileServiceException {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            Option option = optionDAO.findById(Option.class, Integer.valueOf(id), em);
            JpaUtil.commitTransaction(em);
            return option;
        } catch (MobileDAOException e) {
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }

    public List<Option> getAllAnotherOptions(String id) throws MobileServiceException {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            List<Option> options = optionDAO.getAnotherOptions(Integer.valueOf(id), em);
            JpaUtil.commitTransaction(em);
            return options;
        } catch (MobileDAOException e) {
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }

    public List<Option> getAllOptions() throws MobileServiceException {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            List<Option> options = optionDAO.findAll(Option.class, em);
            JpaUtil.commitTransaction(em);
            return options;
        } catch (MobileDAOException e) {
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }

    public void addOption(String name, String price, String connectPrice, String[] requiredOption,
                          String[] incompatibleOption) throws MobileServiceException {
        Option option = new Option();

        if (name.isEmpty()) {
            String message = "Invalid name for option";
            logger.error(message);
            throw new MobileServiceException(message);
        }
        option.setName(name);
        try {
            option.setPrice(Integer.valueOf(price));
            option.setConnectPrice(Integer.valueOf(connectPrice));
        } catch (NumberFormatException e) {
            String message = "Invalid parameters for option";
            logger.error(message);
            throw new MobileServiceException(message, e);
        }

        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            optionDAO.save(option, em);
            em.flush();

            try {
                setRequiredAndIncompatibleOptions(option, requiredOption, incompatibleOption, em);
            } catch (CompatibilityOptionException e){
                logger.error(e.getMessage());
                throw new MobileServiceException(e.getMessage(), e);
            }

            em.merge(option);
            em.flush();
            JpaUtil.commitTransaction(em);
        } catch (MobileDAOException e) {
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }

    public List<Option> getOptionsByName(String name) throws MobileServiceException {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            List<Option> options = optionDAO.getOptionsByName("%" + name + "%", em);
            JpaUtil.commitTransaction(em);
            return options;
        } catch (MobileDAOException e) {
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }

    public void changeOption(String id, String name, String price, String connectPrice,
                             String[] requiredOption, String[] incompatibleOption) throws MobileServiceException {


        if (name.isEmpty()) {
            String message = "Invalid name for option";
            logger.error(message);
            throw new MobileServiceException(message);
        }

        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();

            Option option = optionDAO.findById(Option.class, Integer.valueOf(id), em);
            option.setName(name);

            try {
                option.setPrice(Integer.valueOf(price));
                option.setConnectPrice(Integer.valueOf(connectPrice));
            } catch (NumberFormatException e) {
                String message = "Invalid parameters for option";
                logger.error(message);
                JpaUtil.rollbackTransaction(em);
                throw new MobileServiceException(message, e);
            }

            try {
                setRequiredAndIncompatibleOptions(option, requiredOption, incompatibleOption, em);
            } catch (CompatibilityOptionException e){
                logger.error(e.getMessage());
                throw new MobileServiceException(e.getMessage(), e);
            }

            JpaUtil.commitTransaction(em);
        } catch (MobileDAOException e) {
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }

    private void setRequiredAndIncompatibleOptions(Option option, String[] requiredOption, String[] incompatibleOption,
                                                   EntityManager em) throws MobileDAOException, CompatibilityOptionException{

        List<Option> requiredOptions = new ArrayList<>();
        option.setOptionsRequired(requiredOptions);
        if (requiredOption != null) {
            for (String requiredOptionId : requiredOption) {
                requiredOptions.add(optionDAO.findById(Option.class, Integer.valueOf(requiredOptionId), em));
            }
        }

        List<Option> allOptions = optionDAO.findAll(Option.class, em);

        if (!allOptions.isEmpty() && !requiredOptions.isEmpty()) {
            CompatibilityOptionChecker.checkInterdependentOptions(option, allOptions, requiredOptions);
        }

        List<Option> newIncompatibleOptions = new ArrayList<>();
        option.setOptionsIncompatible(newIncompatibleOptions);

        if (incompatibleOption != null) {
            for (String incompatibleOptionId : incompatibleOption) {
                Option incOption = optionDAO.findById(Option.class, Integer.valueOf(incompatibleOptionId), em);
                newIncompatibleOptions.add(incOption);
                if (!incOption.getOptionsIncompatible().contains(option)) {
                    incOption.getOptionsIncompatible().add(option);
                    CompatibilityOptionChecker.checkRequiredAndIncompatibleIntersection(incOption);
                }
            }
        }

        List<Option> oldIncompatibleOptions = option.getOptionsIncompatible();
        if (!oldIncompatibleOptions.isEmpty()) {
            for (Option oldInc : oldIncompatibleOptions) {
                if (!newIncompatibleOptions.contains(oldInc)) {
                    oldInc = optionDAO.findById(Option.class, oldInc.getId(), em);
                    oldInc.getOptionsIncompatible().remove(option);
                }
            }
        }

        CompatibilityOptionChecker.checkIncompatibleWithRequiredOptions(allOptions, requiredOptions, newIncompatibleOptions);

        option.setOptionsRequired(requiredOptions);
        option.setOptionsIncompatible(newIncompatibleOptions);

        CompatibilityOptionChecker.checkRequiredAndIncompatibleIntersection(option);
    }
}

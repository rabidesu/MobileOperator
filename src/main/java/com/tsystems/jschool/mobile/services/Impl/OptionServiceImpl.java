package com.tsystems.jschool.mobile.services.Impl;

import com.tsystems.jschool.mobile.MobileContext;
import com.tsystems.jschool.mobile.dao.API.OptionDAO;
import com.tsystems.jschool.mobile.dao.Impl.OptionDAOImpl;
import com.tsystems.jschool.mobile.dao.JpaUtil;
import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.exceptions.CompatibilityOptionException;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.API.OptionService;
import com.tsystems.jschool.mobile.services.CompatibilityOptionChecker;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandra on 25.02.2016.
 */
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
        option.setName(name);
        option.setPrice(Integer.valueOf(price));
        option.setConnectPrice(Integer.valueOf(connectPrice));

        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            optionDAO.save(option, em);
            em.flush();
            serRequiredAndIncompatibleOptions(option, requiredOption, incompatibleOption, em);
            try {
                CompatibilityOptionChecker.checkReqAndIncompIntersection(option);
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

        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            Option option = optionDAO.findById(Option.class, Integer.valueOf(id), em);
            option.setName(name);
            option.setPrice(Integer.valueOf(price));
            option.setConnectPrice(Integer.valueOf(connectPrice));
            serRequiredAndIncompatibleOptions(option, requiredOption, incompatibleOption, em);
            JpaUtil.commitTransaction(em);
        } catch (MobileDAOException e) {
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }

    private void serRequiredAndIncompatibleOptions(Option option, String[] requiredOption, String[] incompatibleOption,
                                                   EntityManager em) throws MobileDAOException{
        List<Option> requiredOptions = new ArrayList<>();
        if (requiredOption != null) {
            for (String requiredOptionId : requiredOption) {
                requiredOptions.add(optionDAO.findById(Option.class, Integer.valueOf(requiredOptionId), em));
            }

        }
        option.setOptionsRequired(requiredOptions);
        List<Option> incompatibleOptions = new ArrayList<>();
        if (incompatibleOption != null) {
            for (String incompatibleOptionId : incompatibleOption) {
                incompatibleOptions.add(optionDAO.findById(Option.class, Integer.valueOf(incompatibleOptionId), em));
            }
        }
        option.setOptionsIncompatible(incompatibleOptions);
    }
}

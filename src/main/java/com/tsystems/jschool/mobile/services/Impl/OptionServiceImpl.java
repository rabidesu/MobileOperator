package com.tsystems.jschool.mobile.services.Impl;

import com.tsystems.jschool.mobile.dao.API.OptionDAO;
import com.tsystems.jschool.mobile.dao.Impl.OptionDAOImpl;
import com.tsystems.jschool.mobile.dao.JpaUtil;
import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.services.API.OptionService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandra on 25.02.2016.
 */
public class OptionServiceImpl implements OptionService {

    private OptionDAO optionDAO;

    public void removeOptionById(String id){
        EntityManager em = JpaUtil.getEntityManager();
        optionDAO = new OptionDAOImpl(em);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        optionDAO.removeOptionById(Integer.valueOf(id));
        transaction.commit();
        em.close();
    }

    public Option getOptionById(String id){
        EntityManager em = JpaUtil.getEntityManager();
        optionDAO = new OptionDAOImpl(em);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Option option = optionDAO.findById(Option.class, Integer.valueOf(id));
        transaction.commit();
        em.close();
        return option;
    }

    public List<Option> getAllAnotherOptions(String id){
        EntityManager em = JpaUtil.getEntityManager();
        optionDAO = new OptionDAOImpl(em);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<Option> options = optionDAO.getAnotherOptions(Integer.valueOf(id));
        transaction.commit();
        em.close();
        return options;
    }

    public List<Option> getAllOptions() {
        EntityManager em = JpaUtil.getEntityManager();
        optionDAO = new OptionDAOImpl(em);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<Option> options = optionDAO.findAll(Option.class);
        transaction.commit();
        em.close();
        return options;
    }

    public void addOption(String name, String price, String connectPrice, String[] requiredOption, String[] incompatibleOption) {
        Option option = new Option();
        option.setName(name);
        option.setPrice(Integer.valueOf(price));
        option.setConnectPrice(Integer.valueOf(connectPrice));
        EntityManager em = JpaUtil.getEntityManager();
        optionDAO = new OptionDAOImpl(em);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        optionDAO.save(option);
        em.flush();
        if (requiredOption != null) {
            List<Option> requiredOptions = new ArrayList<Option>();
            for (String requiredOptionId : requiredOption) {
                requiredOptions.add(optionDAO.findById(Option.class, Integer.valueOf(requiredOptionId)));
            }
            option.setOptionsRequired(requiredOptions);
        }
        if (incompatibleOption != null) {
            List<Option> incompatibleOptions = new ArrayList<Option>();
            for (String incompatibleOptionId : incompatibleOption) {
                incompatibleOptions.add(optionDAO.findById(Option.class, Integer.valueOf(incompatibleOptionId)));
            }
            option.setOptionsIncompatible(incompatibleOptions);
        }
        em.merge(option);
        em.flush();
        transaction.commit();
        em.close();
    }

    public List<Option> getOptionsByName(String name){
        EntityManager em = JpaUtil.getEntityManager();
        optionDAO = new OptionDAOImpl(em);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<Option> options = optionDAO.getOptionsByName("%" + name + "%");
        transaction.commit();
        em.close();
        return options;
    }

    public void changeOption(String id, String name, String price, String connectPrice, String[] requiredOption, String[] incompatibleOption) {
        EntityManager em = JpaUtil.getEntityManager();
        optionDAO = new OptionDAOImpl(em);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Option option = optionDAO.findById(Option.class, Integer.valueOf(id));
        option.setName(name);
        option.setPrice(Integer.valueOf(price));
        option.setConnectPrice(Integer.valueOf(connectPrice));
        if (requiredOption != null) {
            List<Option> requiredOptions = new ArrayList<Option>();
            for (String requiredOptionId : requiredOption) {
                requiredOptions.add(optionDAO.findById(Option.class, Integer.valueOf(requiredOptionId)));
            }
            option.setOptionsRequired(requiredOptions);
        }
        if (incompatibleOption != null) {
            List<Option> incompatibleOptions = new ArrayList<Option>();
            for (String incompatibleOptionId : incompatibleOption) {
                incompatibleOptions.add(optionDAO.findById(Option.class, Integer.valueOf(incompatibleOptionId)));
            }
            option.setOptionsIncompatible(incompatibleOptions);
        }
        transaction.commit();
        em.close();
    }
}

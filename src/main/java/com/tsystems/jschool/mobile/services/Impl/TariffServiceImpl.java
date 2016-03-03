package com.tsystems.jschool.mobile.services.Impl;

import com.tsystems.jschool.mobile.MobileContext;
import com.tsystems.jschool.mobile.dao.API.OptionDAO;
import com.tsystems.jschool.mobile.dao.API.TariffDAO;
import com.tsystems.jschool.mobile.dao.Impl.OptionDAOImpl;
import com.tsystems.jschool.mobile.dao.Impl.TariffDAOImpl;
import com.tsystems.jschool.mobile.dao.JpaUtil;
import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import org.apache.log4j.Logger;
import com.tsystems.jschool.mobile.services.API.TariffService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandra on 26.02.2016.
 */
public class TariffServiceImpl implements TariffService {

    private final static Logger logger = Logger.getLogger(TariffServiceImpl.class);

    private TariffDAO tariffDAO;
    private OptionDAO optionDAO;

    public TariffServiceImpl(MobileContext context) {
        tariffDAO = context.tariffDao;
        optionDAO = context.optionDAO;
    }

    public void addTariff(String name, String price, String[] possibleOptions) throws MobileServiceException {

        Tariff tariff = new Tariff();
        tariff.setName(name);
        try {
            tariff.setPrice(Integer.valueOf(price));
        } catch (NumberFormatException e) {
            String message = "Неверное значение для цены тарифа: " + price;
            logger.error(message);
            throw new MobileServiceException(message, e);
        }

        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            setOptionsForTariff(tariff, possibleOptions, em);
            tariffDAO.save(tariff, em);
            JpaUtil.commitTransaction(em);
        } catch (MobileDAOException e) {
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e.getMessage(), e);
        }
    }

    public List<Tariff> getAllTariffs() throws MobileServiceException {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            List<Tariff> tariffs = tariffDAO.findAll(Tariff.class, em);
            JpaUtil.commitTransaction(em);
            return tariffs;
        } catch (MobileDAOException e) {
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }

    public List<Tariff> findTariffByName(String name) throws MobileServiceException {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            List<Tariff> tariffs = tariffDAO.findTariffByName("%" + name + "%", em);
            JpaUtil.commitTransaction(em);
            return tariffs;
        } catch (MobileDAOException e) {
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }

    public Tariff getTariffById(String id) throws MobileServiceException {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            Tariff tariff = tariffDAO.findById(Tariff.class, Integer.valueOf(id), em);
            JpaUtil.commitTransaction(em);
            return tariff;
        } catch (MobileDAOException e) {
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }

    public void removeTariffById(String id) throws MobileServiceException {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            tariffDAO.removeTariffById(Integer.valueOf(id), em);
            JpaUtil.commitTransaction(em);
        } catch (MobileDAOException e) {
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }

    public void changeTariff(String id, String name, String price, String[] possibleOptions) throws MobileServiceException {
        EntityManager em = null;
        try {
            em = JpaUtil.beginTransaction();
            Tariff tariff = tariffDAO.findById(Tariff.class, Integer.valueOf(id), em);
            tariff.setName(name);
            tariff.setPrice(Integer.valueOf(price));
            setOptionsForTariff(tariff, possibleOptions, em);
            JpaUtil.commitTransaction(em);
        } catch (MobileDAOException e) {
            JpaUtil.rollbackTransaction(em);
            throw new MobileServiceException(e);
        }
    }

    private void setOptionsForTariff(Tariff tariff, String[] possibleOptions, EntityManager em) throws MobileDAOException{
        if (possibleOptions != null) {
            List<Option> options = new ArrayList<Option>();
            for (String optionId : possibleOptions) {
                options.add(optionDAO.findById(Option.class, Integer.valueOf(optionId), em));
            }
            tariff.setOptions(options);
        }
    }
}
